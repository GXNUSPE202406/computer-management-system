package com.gxnu.service;

import com.gxnu.pojo.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxnu.utils.Result;

/**
* @author 王功磊
* @description 针对表【room】的数据库操作Service
* @createDate 2024-07-07 11:53:03
*/
public interface RoomService extends IService<Room> {

    Result addRoom(String roomName);

    Result delRoom(String roomId);

    Result modifyRoom(String roomId, String roomName);

    String findRoomNameById(Integer roomId);
}
