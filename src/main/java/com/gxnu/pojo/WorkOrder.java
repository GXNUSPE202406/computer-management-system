package com.gxnu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName work_order
 */
@Data
public class WorkOrder implements Serializable {
    @TableId
    private Integer workId;

    private Integer studentId;

    private Integer computerId;

    private Integer roomId;

    private Date beginTime;

    private Integer isDeleted;

    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
}