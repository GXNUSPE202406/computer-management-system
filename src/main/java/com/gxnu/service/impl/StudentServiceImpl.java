package com.gxnu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.pojo.Student;
import com.gxnu.service.StudentService;
import com.gxnu.mapper.StudentMapper;
import com.gxnu.utils.JwtHelper;
import com.gxnu.utils.MD5Util;
import com.gxnu.utils.Result;
import com.gxnu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 王功磊
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-07-07 11:53:03
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(Student student) {
        //根据学生邮箱查询数据
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentEmail, student.getStudentEmail());
        Student loginStudent = studentMapper.selectOne(queryWrapper);

        //邮箱不存在
        if (loginStudent == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //对比密码
        if (!StringUtils.isEmpty(student.getStudentPassword())
                && MD5Util.encrypt(student.getStudentPassword()).equals(loginStudent.getStudentPassword())) {
            //登陆成功
            //根据用户id生成token
            //将token封装到Result返回
            String token = jwtHelper.createToken(Long.valueOf(loginStudent.getStudentId()));
            Map data= new HashMap();
            data.put("token", token);

            return Result.ok(data);
        }

        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getStuInfo(String token) {
        //token是否过期，如果为true表示过期
        boolean expiration = jwtHelper.isExpiration(token);

        //token失效
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        int stuId = jwtHelper.getUserId(token).intValue();

        Student student = studentMapper.selectById(stuId);
        student.setStudentPassword("");

        Map data = new HashMap();
        data.put("loginStu", student);

        return Result.ok(data);
    }

    @Override
    public Result checkStuEmail(String stuEmail) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentEmail, stuEmail);
        Student student = studentMapper.selectOne(queryWrapper);

        // 邮箱已存在
        if (student != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        return Result.ok(null);
    }

    @Override
    public Result checkForgetStuEmail(String email) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentEmail, email);
        Student student = studentMapper.selectOne(queryWrapper);

        // 邮箱存在
        if (student != null) {
            return Result.ok(null);
        }

        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }


    @Override
    public Result regist(Student student) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentEmail, student.getStudentEmail());
        Long count = studentMapper.selectCount(queryWrapper);

        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        student.setStudentPassword(MD5Util.encrypt(student.getStudentPassword()));

        int rows = studentMapper.insert(student);
        System.out.println("rows = " + rows);

        return Result.ok(null);
    }

    @Override
    public Result passwordChange(Student student) {
        LambdaUpdateWrapper<Student> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Student::getStudentEmail, student.getStudentEmail())
                .set(Student::getStudentPassword, student.getStudentPassword());

        this.update(null, updateWrapper);

        return Result.ok(null);
    }

    @Override
    public String findStuName(Integer studentId) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Student::getStudentId, studentId);

        Student student = studentMapper.selectOne(queryWrapper);

        return student.getStudentName();
    }


}




