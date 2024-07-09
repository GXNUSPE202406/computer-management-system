package com.gxnu.controller;

import com.gxnu.pojo.Machine;
import com.gxnu.pojo.PortalVo;
import com.gxnu.pojo.Room;
import com.gxnu.service.MachineInfoService;
import com.gxnu.service.MachineService;
import com.gxnu.service.RoomService;
import com.gxnu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping ("addMachine")
    public Result addMachine(@RequestBody Machine machine) {
        Result result = machineService.addMachine(machine);
        return result;
    }

    @PostMapping ("delMachine")
    public Result delMachine(@RequestBody String computerId) {
        Result result = machineService.delMachine(computerId);
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
        Result result = machineService.findRoomMachines(roomId);
        return result;
    }

    @PostMapping("findNewPage")
    public Result findNewPage(@RequestBody PortalVo portalVo){
        Result result = machineService.findNewPage(portalVo);
        return result;
    }
}
