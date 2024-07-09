package com.gxnu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxnu.pojo.MachineInfo;
import com.gxnu.utils.Result;

public interface MachineInfoService extends IService<MachineInfo> {
    Result addMachineInfo(MachineInfo machineInfo);

    Result findRoomMachines(String roomId);
}
