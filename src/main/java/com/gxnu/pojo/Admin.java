package com.gxnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName admin
 */
@Data
public class Admin implements Serializable {
    @TableId
    private Integer adminId;

    private String adminUsername;

    private String adminPassword;

    private static final long serialVersionUID = 1L;
}