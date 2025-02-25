package com.qucoon.hospitalmanagement.model.request;

import com.qucoon.hospitalmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCreateRequest {
    private String firstName;
    private String lastName;
    private String email;

    private String PhoneNumber;
    private Gender gender;
    private String address;
    private String emergencyContact;
}
