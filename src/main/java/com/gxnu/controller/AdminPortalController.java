package com.gxnu.controller;

import com.gxnu.DTO.TotalInfo;
import com.gxnu.DTO.TotalRequest;
import com.gxnu.pojo.Machine;
import com.gxnu.pojo.PortalVo;
import com.gxnu.pojo.Record;
import com.gxnu.pojo.Room;
import com.gxnu.service.MachineService;
import com.gxnu.service.RecordService;
import com.gxnu.service.RoomService;
import com.gxnu.service.StudentService;
import com.gxnu.utils.Result;
import com.gxnu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("adminPortal")
@CrossOrigin
public class AdminPortalController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private StudentService studentService;


    @PostMapping ("addMachine")
    public Result addMachine(@RequestBody Machine machine) {
        Result result = machineService.addMachine(machine);
        return result;
    }

    @GetMapping ("delMachine/{computerId}")
    public Result delMachine(@PathVariable String computerId) {
        Result result = machineService.delMachine(computerId);
        return result;
    }

    @PostMapping("modifyMachine")
    public Result modifyMachine(@RequestBody Machine machine) {
        Result result = machineService.modifyMachine(machine);
        return result;
    }

    @GetMapping("findAllRoom")
    public Result findAllRoom() {
        List<Room> list = roomService.list();
        Long count = roomService.count();
        Map map = new HashMap();
        map.put("count", count);
        map.put("data", list);
        return Result.ok(map);
    }

    @GetMapping("addRoom")
    public Result addRoom(@RequestParam String roomName) {
        Result result = roomService.addRoom(roomName);
        return Result.ok(null);
    }

    @GetMapping("delRoom")
    public Result delRoom(@RequestParam String roomId) {
        Result result = roomService.delRoom(roomId);
        return result;
    }

    @GetMapping("modifyRoom")
    public Result modifyRoom(@RequestParam String roomId, @RequestParam String roomName) {
        Result result = roomService.modifyRoom(roomId, roomName);
        return result;
    }

    @GetMapping("findRoomMachines/{roomId}")
    public Result findRoomMachines(@PathVariable String roomId) {
        if (roomId == null) {
            return Result.fair(null);
        }
        Result result = machineService.findRoomMachines(roomId);
        return result;
    }

    @PostMapping("findNewPage")
    public Result findNewPage(@RequestBody PortalVo portalVo){
        Result result = machineService.findNewPage(portalVo);
        return result;
    }

    @PostMapping("total")
    public Result findTotal(@RequestBody TotalRequest totalRequest) {
        Integer roomId = Integer.parseInt(totalRequest.getRoomId());
        Timestamp starDate = Timestamp.valueOf(totalRequest.getStartDate());
        Timestamp endDate = Timestamp.valueOf(totalRequest.getEndDate());

//        System.out.println(roomId);
//        System.out.println(starDate);
//        System.out.println(endDate);

        List<Record> list = recordService.getRecordsBetweenDates(roomId, starDate, endDate);
        List<TotalInfo> resultList = new ArrayList<>();

        // 创建一个 SimpleDateFormat 对象，指定日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int count = list.size();
        int all = 0;

        for (Record e : list) {
            TotalInfo totalInfo = new TotalInfo();
            Integer studentId = e.getStudentId();
            String studentName = studentService.findStuName(studentId);
            Integer machineId = e.getComputerId();
            Machine machine = machineService.findMachine(machineId);
            totalInfo.setBeginTime(dateFormat.format(e.getBeginTime()));
            totalInfo.setEndTime(dateFormat.format(e.getEndTime()));
            totalInfo.setCost(e.getCost());
            totalInfo.setCpuModel(machine.getCpuModel());
            totalInfo.setGpuModel(machine.getGpuModel());
            totalInfo.setUserName(studentName);
            totalInfo.setScreenModel(machine.getScreenModel());
            all += e.getCost();
            resultList.add(totalInfo);
        }

        Map map = new HashMap<>();
        map.put("data", resultList);
        map.put("count", count);
        map.put("all", all);

        return Result.build(map, ResultCodeEnum.SUCCESS);
    }
}
