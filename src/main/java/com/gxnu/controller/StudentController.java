package com.gxnu.controller;

import com.gxnu.DTO.ApplicationInfo;
import com.gxnu.pojo.Machine;
import com.gxnu.pojo.Student;
import com.gxnu.pojo.StudentRegistrationRequest;
import com.gxnu.pojo.WorkOrder;
import com.gxnu.service.MachineService;
import com.gxnu.service.RoomService;
import com.gxnu.service.StudentService;
import com.gxnu.service.WorkOrderService;
import com.gxnu.utils.MailMsg;
import com.gxnu.utils.Result;
import com.gxnu.utils.ResultCodeEnum;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MailMsg mailMsg;

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private RoomService roomService;


    //登录
    @PostMapping("login")
    public Result login(@RequestBody Student student) {
        Result result = studentService.login(student);
        System.out.println("result = " + result);
        return result;
    }

    //通过token获取用户信息
    @GetMapping("getStuInfo")
    public Result getStuInfo(@RequestHeader String token) {
        Result result = studentService.getStuInfo(token);
        return result;
    }

    //验证邮箱是否被注册
    @GetMapping("checkStuEmail/{email}")
    public Result checkStuEmail(@PathVariable String email) {
        Result result = studentService.checkStuEmail(email);
        return result;
    }

    //注册
    @PostMapping("regist")
    public Result regist(@RequestBody StudentRegistrationRequest request){
        Student student = request.getStudent();
        String emailVerificationCode = request.getEmailVerificationCode();

         boolean isCodeValid = emailVerificationCode.equals(redisTemplate.opsForValue().get(student.getStudentEmail()));
         if (!isCodeValid) {
             return Result.fair(null);
         }

        Result result = studentService.regist(student);
        return result;
    }

    @GetMapping("sendEmail/{email}")
    public Result sendCode(@PathVariable String email) throws MessagingException {
        // 从 Redis 中取出验证码信息
        String code = redisTemplate.opsForValue().get(email);

        if (!StringUtils.isEmpty(code)) {
            Map data = new HashMap<>();
            data.put("code", code);
            return Result.build(data, ResultCodeEnum.CODE_EXIST);
        }
        boolean b = mailMsg.mail(email);
        if (b) {
            return Result.ok(null);
        }
        return Result.fair(null);
    }

    @PostMapping("useMachineInfo")
    public Result useMachineInfo(@RequestBody Student student) {

        List<WorkOrder> list = workOrderService.findByStuId(student.getStudentId());
        List<ApplicationInfo> resultList = new ArrayList<>();
        int count = list.size();

        if (count == 0) {
            Result.build(null, ResultCodeEnum.NULL);
        }

        Date date = new Date();
        // 创建一个SimpleDateFormat对象，指定日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (WorkOrder e : list) {
            ApplicationInfo applicationInfo = new ApplicationInfo();
            applicationInfo.setWorkId(e.getWorkId());

            Machine machine = machineService.findMachine(e.getComputerId());

            applicationInfo.setGpuModel(machine.getGpuModel());
            applicationInfo.setCpuModel(machine.getCpuModel());
            applicationInfo.setScreenModel(machine.getScreenModel());

            String roomName = roomService.findRoomNameById(e.getRoomId());
            applicationInfo.setRoomName(roomName);

            String beginTime = formatter.format(e.getBeginTime());
            applicationInfo.setBeginTime(beginTime);

            long d = date.getTime() - e.getBeginTime().getTime();
            long hour = (long) Math.ceil((double) d / (1000 * 60 * 60));
            applicationInfo.setCost((int)hour);

            resultList.add(applicationInfo);
        }

        Map map = new HashMap<>();
        map.put("data", resultList);
        map.put("count", count);

        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

}
