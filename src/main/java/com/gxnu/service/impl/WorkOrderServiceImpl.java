package com.gxnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.pojo.WorkOrder;
import com.gxnu.service.WorkOrderService;
import com.gxnu.mapper.WorkOrderMapper;
import com.gxnu.utils.Result;
import com.gxnu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 王功磊
* @description 针对表【work_order】的数据库操作Service实现
* @createDate 2024-07-07 11:53:03
*/
@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder>
    implements WorkOrderService{

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Override
    public List<WorkOrder> findByStuId(Integer studentId) {
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkOrder::getStudentId, studentId);

        List<WorkOrder> list = workOrderMapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public Result del(WorkOrder workOrder) {
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkOrder::getWorkId, workOrder.getWorkId());
        workOrderMapper.delete(queryWrapper);
        return Result.ok(null);
    }

    @Override
    public WorkOrder findById(Integer workId) {
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkOrder::getWorkId, workId);

        WorkOrder workOrder = workOrderMapper.selectOne(queryWrapper);

        System.out.println("tt");

        return workOrder;
    }

    @Override
    public boolean findUsable(Integer computerId) {
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkOrder::getComputerId, computerId);

        WorkOrder workOrder = workOrderMapper.selectOne(queryWrapper);

        if (workOrder == null) {
            return false;
        }

        return true;
    }

    @Override
    public Result add(WorkOrder workOrder) {
        workOrderMapper.insert(workOrder);
        return Result.ok(null);
    }

    @Override
    public void delByComputerId(String computerId) {
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkOrder::getComputerId, computerId);

        workOrderMapper.delete(queryWrapper);
    }
}




