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
    public Result findByStuId(Integer studentId) {
        LambdaQueryWrapper<WorkOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkOrder::getStudentId, studentId);

        WorkOrder workOrder = workOrderMapper.selectOne(queryWrapper);

        return Result.build(workOrder, ResultCodeEnum.SUCCESS);
    }
}




