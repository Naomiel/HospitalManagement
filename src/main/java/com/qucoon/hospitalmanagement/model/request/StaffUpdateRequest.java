package com.qucoon.hospitalmanagement.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffUpdateRequest {
    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
    private String staffDepartment;
    private String staffPhoneNumber;
    private String staffEmail;
    private Double staffSalary;
    private String staffStatus;
    private String staffHireDate;
}
