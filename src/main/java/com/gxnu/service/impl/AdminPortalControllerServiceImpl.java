package com.gxnu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxnu.mapper.MachineInfoMapper;
import com.gxnu.pojo.MachineInfo;
import com.gxnu.service.AdminPortalControllerService;
import org.springframework.stereotype.Service;

@Service
public class AdminPortalControllerServiceImpl extends ServiceImpl<MachineInfoMapper, MachineInfo>
        implements AdminPortalControllerService {


}
