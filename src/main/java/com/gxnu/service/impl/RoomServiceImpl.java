package com.gxnu.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.pojo.Room;
import com.gxnu.service.RoomService;
import com.gxnu.mapper.RoomMapper;
import com.gxnu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 王功磊
* @description 针对表【room】的数据库操作Service实现
* @createDate 2024-07-07 11:53:03
*/
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room>
    implements RoomService{

    @Autowired
    private RoomMapper roomMapper;
    

    @Override
    public Result addRoom(String roomName) {
        Room room = new Room();
        room.setRoomName(roomName);
        room.setMachineNumber(0);
        roomMapper.insert(room);
        return Result.ok(null);
    }

    @Override
    public Result delRoom(String roomId) {
        roomMapper.deleteById(Integer.parseInt(roomId));
        return Result.ok(null);
    }

    @Override
    public Result modifyRoom(String roomId, String roomName) {
        LambdaUpdateWrapper<Room> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Room::getRoomId, Integer.parseInt(roomId)).set(Room::getRoomName,roomName);

        this.update(null, updateWrapper);

        return Result.ok(null);
    }
}




