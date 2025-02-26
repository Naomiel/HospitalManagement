package com.qucoon.hospitalmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.qucoon.hospitalmanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String phoneNumber;
    private Gender gender;
    private String address;
    private String emergencyContact;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
