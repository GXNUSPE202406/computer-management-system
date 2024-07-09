package com.gxnu.controller;

import com.gxnu.pojo.Admin;
import com.gxnu.service.AdminService;
import com.gxnu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public Result login(@RequestBody Admin admin) {
        Result result = adminService.login(admin);
        System.out.println("result = " + result);
        return result;
    }

}
