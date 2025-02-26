package com.qucoon.hospitalmanagement.model.request;

import com.qucoon.hospitalmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String phoneNumber;
    private Gender gender;
    private String address;
    private String emergencyContact;
}
