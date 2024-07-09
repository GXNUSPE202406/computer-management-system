package com.gxnu.pojo;

import lombok.Data;

@Data
public class MachineInfo {

    private String gpuModel;

    private String cpuModel;

    private String screenModel;

    private Integer costPerHour;

    private String roomName;
}
