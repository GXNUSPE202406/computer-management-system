package com.gxnu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.mapper.StudentMapper;
import com.gxnu.pojo.Admin;
import com.gxnu.pojo.Student;
import com.gxnu.service.AdminService;
import com.gxnu.mapper.AdminMapper;
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
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2024-07-07 11:53:03
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(Admin admin) {
        //根据管理员用户名查询数据
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminUsername, admin.getAdminUsername());
        Admin loginAdmin = adminMapper.selectOne(queryWrapper);

        //用户名不存在
        if (loginAdmin == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //对比密码
        if (!StringUtils.isEmpty(admin.getAdminPassword())
                && MD5Util.encrypt(admin.getAdminPassword()).equals(loginAdmin.getAdminPassword())) {
            //登陆成功
            //根据管理员生成token
            //将token封装到Result返回
            String token = jwtHelper.createToken(Long.valueOf(loginAdmin.getAdminId()));
            Map data= new HashMap();
            data.put("token", token);

            return Result.ok(data);
        }

        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }
}




