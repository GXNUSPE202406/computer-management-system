package com.gxnu.controller;

import com.gxnu.pojo.MachineInfo;
import com.gxnu.pojo.PortalVo;
import com.gxnu.pojo.Room;
import com.gxnu.service.AdminPortalControllerService;
import com.gxnu.service.MachineService;
import com.gxnu.service.RoomService;
import com.gxnu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adminPortal")
@CrossOrigin
public class AdminPortalController {

    @Autowired
    private AdminPortalControllerService adminPortalControllerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MachineService machineService;

    @GetMapping ("addMachineInfo")
    public Result addMachine(@RequestBody MachineInfo machineInfo) {
        Result result = adminPortalControllerService.addMachineInfo(machineInfo);
        return result;
    }

    @GetMapping("findAllRoom")
    public Result findAllRoom() {
        List<Room> list = roomService.list();
        return Result.ok(list);
    }


    @PostMapping("findNewPage")
    public Result findNewPage(@RequestBody PortalVo portalVo){
        Result result = machineService.findNewPage(portalVo);
        return result;
    }
}
