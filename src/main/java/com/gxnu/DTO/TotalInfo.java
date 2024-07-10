package com.gxnu.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TotalInfo {
    private String userName;

    private String screenModel;

    private String cpuModel;

    private String gpuModel;

    private String beginTime;

    private String endTime;

    private Integer cost;
}
