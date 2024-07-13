package com.gxnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.pojo.Record;
import com.gxnu.pojo.WorkOrder;
import com.gxnu.service.RecordService;
import com.gxnu.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
* @author 王功磊
* @description 针对表【record】的数据库操作Service实现
* @createDate 2024-07-07 11:53:03
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<Record> getRecordsBetweenDates(Integer roomId, Timestamp startDate, Timestamp endDate) {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getRoomId, roomId)
                .ge(Record::getBeginTime, startDate)
                .le(Record::getEndTime, endDate);

        List<Record> list = recordMapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public void add(Record record) {

        recordMapper.insert(record);
        System.out.println("tt");
    }

    @Override
    public void delByComputerId(String computerId) {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getComputerId, computerId);
        recordMapper.delete(queryWrapper);
    }


}




