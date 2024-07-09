package com.gxnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.mapper.MachineInfoMapper;
import com.gxnu.mapper.MachineMapper;
import com.gxnu.mapper.RoomMapper;
import com.gxnu.pojo.Machine;
import com.gxnu.pojo.MachineInfo;
import com.gxnu.pojo.Room;
import com.gxnu.service.MachineInfoService;
import com.gxnu.utils.Result;
import com.gxnu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MachineInfoServiceImpl  extends ServiceImpl<MachineInfoMapper, MachineInfo>
        implements MachineInfoService {

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

    @Override
    public Result findRoomMachines(String roomId) {
        // 查询Machine表
        LambdaQueryWrapper<Machine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Machine::getRoomId, Integer.parseInt(roomId)).eq(Machine::getIsDeleted, 0);

        List<Machine> machineList = machineMapper.selectList(queryWrapper);
        Long count = machineMapper.selectCount(queryWrapper);

        // 查询Room表获取roomName
        Room room = roomMapper.selectById(Integer.parseInt(roomId));
        String roomName = room != null ? room.getRoomName() : "";

        // 转换Machine对象为MachineInfo对象
        List<MachineInfo> machineInfoList = machineList.stream().map(machine -> {
            MachineInfo machineInfo = new MachineInfo();
            machineInfo.setGpuModel(machine.getGpuModel());
            machineInfo.setCpuModel(machine.getCpuModel());
            machineInfo.setScreenModel(machine.getScreenModel());
            machineInfo.setCostPerHour(machine.getCostPerHour());
            machineInfo.setRoomName(roomName);
            return machineInfo;
        }).collect(Collectors.toList());

        // 构建返回结果
        Map map = new HashMap<>();
        map.put("count", count);
        map.put("data", machineInfoList);

        return Result.build(map, ResultCodeEnum.SUCCESS);
    }
}
