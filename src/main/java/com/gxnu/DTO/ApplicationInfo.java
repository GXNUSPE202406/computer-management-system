package com.gxnu.DTO;

import lombok.Data;

@Data
public class ApplicationInfo {

    private Integer workId;

    private String roomName;

    private String screenModel;

    private String cpuModel;

    private String gpuModel;

    private String beginTime;

    private Integer cost;
}
