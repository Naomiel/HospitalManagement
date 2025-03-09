package com.qucoon.hospitalmanagement.model.request;

import com.qucoon.hospitalmanagement.enums.Gender;
import com.qucoon.hospitalmanagement.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCreateRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "last Name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @Email(message = "staffEmail should be valid")
    @NotBlank(message = "Age is required")
    private Integer age;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Gender is required and must be in Capital letters.")
    private Gender gender;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Emergency Contact is required")
    private String emergencyContact;
    private Status status;
}
