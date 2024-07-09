package com.gxnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.mapper.MachineInfoMapper;
import com.gxnu.mapper.MachineMapper;
import com.gxnu.mapper.RoomMapper;
import com.gxnu.pojo.Machine;
import com.gxnu.pojo.MachineInfo;
import com.gxnu.pojo.Room;
import com.gxnu.service.AdminPortalControllerService;
import com.gxnu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPortalControllerServiceImpl extends ServiceImpl<MachineInfoMapper, MachineInfo>
        implements AdminPortalControllerService {

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Result addMachineInfo(MachineInfo machineInfo) {
        LambdaQueryWrapper<Room> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Room::getRoomName, machineInfo.getRoomName());
        Room room = roomMapper.selectOne(queryWrapper);

        if (room == null) {
            return Result.fair(null);
        }

        Machine machine = new Machine();
        machine.setGpuModel(machineInfo.getGpuModel());
        machine.setCpuModel(machineInfo.getCpuModel());
        machine.setScreenModel(machineInfo.getScreenModel());
        machine.setCostPerHour(machineInfo.getCostPerHour());
        machine.setRoomId(room.getRoomId());
        machine.setIsDeleted(0);
        machine.setVersion(1);

        machineMapper.insert(machine);

        room.setMachineNumber(room.getMachineNumber() + 1);
        roomMapper.updateById(room);

        return Result.ok(null);
    }
}
