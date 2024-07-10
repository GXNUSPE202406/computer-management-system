package com.gxnu.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TotalRequest {
    private Integer roomId;
    private Timestamp startDate;
    private Timestamp endDate;
}
