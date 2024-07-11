package com.gxnu.service;

import com.gxnu.pojo.WorkOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 王功磊
* @description 针对表【work_order】的数据库操作Service
* @createDate 2024-07-07 11:53:03
*/
public interface WorkOrderService extends IService<WorkOrder> {

    List<WorkOrder> findByStuId(Integer studentId);
}
