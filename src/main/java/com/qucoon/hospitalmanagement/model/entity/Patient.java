package com.qucoon.hospitalmanagement.model.entity;


import com.qucoon.hospitalmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date DOB;
    private String phoneNumber;
    private Gender gender;
    private String address;
    private String emergencyContact;
    private String status;
    private String createdAt;
    private String updatedAt;

}
