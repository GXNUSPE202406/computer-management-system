package com.gxnu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName record
 */
@Data
public class Record implements Serializable {
    @TableId
    private Integer recordId;

    private Integer studentId;

    private Integer computerId;

    private Integer roomId;

    private Date beginTime;

    private Date endTime;

    private Integer cost;

    private Integer isDeleted;

    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
}