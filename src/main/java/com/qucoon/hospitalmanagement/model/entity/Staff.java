package com.qucoon.hospitalmanagement.model.entity;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Staff {
    private int staffId;
    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
    private String staffDepartment;
    private String staffPhoneNumber;
    private String staffEmail;
    private Double staffSalary;
    private String staffHireDate;
    private String staffStatus;
    private String staffCreatedAt;
    private String staffUpdatedAt;
}
