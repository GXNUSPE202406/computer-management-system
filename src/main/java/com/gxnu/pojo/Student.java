package com.gxnu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName student
 */
@Data
public class Student implements Serializable {
    @TableId
    private Integer studentId;

    private String studentNumber;

    private String studentPassword;

    private String studentEmail;

    private String studentName;

    private String studentSex;

    private String studentAcademy;

    private String studentMajor;

    private Integer totalTime;

    private Integer totalCost;

    private Integer isDeleted;

    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
}