package com.gxnu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName room
 */
@Data
public class Room implements Serializable {
    @TableId
    private Integer roomId;

    private String roomName;

    private Integer machineNumber;

    private Integer isDeleted;

    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
}