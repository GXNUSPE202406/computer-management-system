package com.gxnu.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TotalRequest {
    private String roomId;
    private String startDate;
    private String endDate;
}
