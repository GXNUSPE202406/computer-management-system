package com.gxnu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.gxnu.mapper.RoomMapper;
import com.gxnu.pojo.Machine;
import com.gxnu.pojo.MachineInfo;
import com.gxnu.pojo.PortalVo;
import com.gxnu.pojo.Room;
import com.gxnu.service.MachineService;
import com.gxnu.mapper.MachineMapper;
import com.gxnu.utils.Result;
import com.gxnu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 王功磊
* @description 针对表【machine】的数据库操作Service实现
* @createDate 2024-07-07 11:53:03
*/
@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine>
    implements MachineService{

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Result findNewPage(PortalVo portalVo) {

//        //1.条件拼接 需要非空判断
//        LambdaQueryWrapper<Machine> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()),Machine::getTitle,portalVo.getKeyWords())
//                .eq(portalVo.getRoomId()!= null,Machine::getRoomId,portalVo.getRoomId());
//
//        //2.分页参数
//        IPage<Machine> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
//
//        //3.分页查询
//        //查询的结果 "pastHours":"3"   // 发布时间已过小时数 我们查询返回一个map
//        //自定义方法
//        headlineMapper.selectPageMap(page, portalVo);
//
//        //4.结果封装
//        //分页数据封装
//        Map<String,Object> pageInfo =new HashMap<>();
//        pageInfo.put("pageData",page.getRecords());
//        pageInfo.put("pageNum",page.getCurrent());
//        pageInfo.put("pageSize",page.getSize());
//        pageInfo.put("totalPage",page.getPages());
//        pageInfo.put("totalSize",page.getTotal());
//
//        Map<String,Object> pageInfoMap=new HashMap<>();
//        pageInfoMap.put("pageInfo",pageInfo);
//        // 响应JSON
//        return Result.ok(pageInfoMap);

        return null;
    }

    @Override
    public Result addMachine(Machine machine) {
        System.out.println("Entering addMachine method with machine: " + machine);
        machineMapper.insert(machine);

        Room room = roomMapper.selectById(machine.getRoomId());
        room.setMachineNumber(room.getMachineNumber() + 1);
        roomMapper.updateById(room);

        return Result.ok(null);
    }

    @Override
    public Result delMachine(String computerId) {
        machineMapper.deleteById(Integer.parseInt(computerId));
        return Result.ok(null);
    }

    @Override
    public Result findRoomMachines(String roomId) {
        LambdaQueryWrapper<Machine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Machine::getRoomId, Integer.parseInt(roomId));

        List<Machine> list = machineMapper.selectList(queryWrapper);
        Long count = machineMapper.selectCount(queryWrapper);

        Map map = new HashMap();
        map.put("count", count);
        map.put("data", list);

        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result modifyMachine(Machine machine) {
        int row = machineMapper.updateById(machine);
        if (row <= 0) {
            return Result.fair(null);
        }

        return Result.ok(null);
    }

    @Override
    public Machine findMachine(Integer machineId) {
        LambdaQueryWrapper<Machine> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Machine::getComputerId, machineId);

        Machine machine = machineMapper.selectOne(queryWrapper);

        return machine;
    }

}




