package com.gxnu.pojo;

import lombok.Data;

@Data
public class StudentRegistrationRequest {
    private Student student;
    private String emailVerificationCode;

}
