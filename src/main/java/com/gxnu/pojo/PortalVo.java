package com.gxnu.pojo;

import lombok.Data;

@Data
public class PortalVo {
    
    private String keyWords;
    private Integer roomId;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}