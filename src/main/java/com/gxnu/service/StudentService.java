package com.gxnu.service;

import com.gxnu.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxnu.utils.Result;

/**
* @author 王功磊
* @description 针对表【student】的数据库操作Service
* @createDate 2024-07-07 11:53:03
*/
public interface StudentService extends IService<Student> {

    // 学生登录
    Result login(Student student);

    // 根据token获取用户数据
    Result getStuInfo(String token);

    // 检查邮箱是否可用
    Result checkStuEmail(String stuEmail);

    Result regist(Student student);

    String findStuName(Integer studentId);
}
