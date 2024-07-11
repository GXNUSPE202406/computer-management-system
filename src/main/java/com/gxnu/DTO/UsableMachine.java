package com.gxnu.DTO;

import lombok.Data;

@Data
public class UsableMachine {
    private Integer roomId;

    private Integer computerId;

    private String roomName;

    private String gpuModel;

    private String cpuModel;

    private String screenModel;

    private Integer costPerHour;


}
