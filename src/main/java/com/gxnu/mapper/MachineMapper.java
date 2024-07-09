package com.gxnu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxnu.pojo.Machine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxnu.pojo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 王功磊
* @description 针对表【machine】的数据库操作Mapper
* @createDate 2024-07-07 11:53:03
* @Entity com.gxnu.pojo.Machine
*/
public interface MachineMapper extends BaseMapper<Machine> {

    //自定义分页查询方法
    IPage<Map> selectPageMap(IPage<Machine> page,
                             @Param("portalVo") PortalVo portalVo);

}




