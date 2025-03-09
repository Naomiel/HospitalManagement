package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.enums.Gender;
import com.qucoon.hospitalmanagement.enums.Status;
import lombok.Data;

@Data
public class PatientResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String phoneNumber;
    private Gender gender;
    private String address;
    private String emergencyContact;
    private Status status;

}
