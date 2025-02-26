//package com.qucoon.hospitalmanagement.model.request;
//
//import lombok.Builder;
//import lombok.Data;
//
//@Data
//@Builder
//public class StaffCreateRequest {
//    private String staffFirstName;
//    private String staffLastName;
//    private String staffRole;
//    private String staffDepartment;
//    private String staffPhoneNumber;
//    private String staffEmail;
//    private double staffSalary;
//    private String staffHireDate;
//}

package com.qucoon.hospitalmanagement.model.request;

import com.qucoon.hospitalmanagement.validation.ValidDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffCreateRequest {
    @NotBlank(message = "staffFirstName  is required")
    private String staffFirstName;

    @NotBlank(message = "staffLastName is required")
    private String staffLastName;

    @NotBlank(message = "staffRole is required")
    private String staffRole;

    @NotBlank(message = "staffDepartment is required")
    private String staffDepartment;

    @NotBlank(message = "staffPhoneNumber number is required")
    private String staffPhoneNumber;

    @Email(message = "staffEmail should be valid")
    @NotBlank(message = "staffEmail is required")
    private String staffEmail;

    @Positive(message = "staffSalary must be greater than zero")
    private double staffSalary;

    @NotBlank(message = "staffHireDate date is required")
//    @ValidDate(message = "staffHireDate must be a valid date in the format yyyy-MM-dd")
    private String staffHireDate;
}
