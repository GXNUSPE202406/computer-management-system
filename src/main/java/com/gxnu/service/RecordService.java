package com.gxnu.service;

import com.gxnu.pojo.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxnu.pojo.WorkOrder;

import java.sql.Timestamp;
import java.util.List;

/**
* @author 王功磊
* @description 针对表【record】的数据库操作Service
* @createDate 2024-07-07 11:53:03
*/
public interface RecordService extends IService<Record> {

    List<Record> getRecordsBetweenDates(Integer roomId, Timestamp starDate, Timestamp endDate);

    void add(Record record);

    void delByComputerId(String computerId);
}
