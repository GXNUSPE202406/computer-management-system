package com.gxnu.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class totalInfo {
    private String useName;

    private String screenModel;

    private String cpuModel;

    private String gpuModel;

    private Timestamp beginTime;

    private Timestamp endTime;

    private Integer cost;
}
