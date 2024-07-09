package com.gxnu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName machine
 */
@Data
public class Machine implements Serializable {
    @TableId
    private Integer computerId;

    private String gpuModel;

    private String cpuModel;

    private String screenModel;

    private Integer costPerHour;

    private Integer roomId;

    private Integer isDeleted;

    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
}