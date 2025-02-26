package com.qucoon.hospitalmanagement.repository.query;

public class PatientQuery {
    public static final String INSERT_PATIENT = """
                INSERT INTO Patient (patientFirstName, patientLastName, patientEmail, patientPhoneNumber,
                                     patientGender, patientAge, patientAddress, patientEmergencyContact,patientStatus,
                                     patientCreatedAt, patientUpdatedAt)
                VALUES (:patientFirstName, :patientLastName, :patientEmail, :patientPhoneNumber,
                        :patientGender, :patientAge, :patientAddress, :patientEmergencyContact,COALESCE(:patientStatus,'ACTIVE'),
                        NOW(), NOW())
            """;

    public static final String GET_ALL = """
                SELECT patientId, patientFirstName, patientLastName, patientEmail, patientPhoneNumber,
                       patientGender, patientAge, patientAddress, patientEmergencyContact, patientStatus,
                       patientCreatedAt, patientUpdatedAt
                FROM Patient
            """;

    public static final String GET_BY_ID = """
                SELECT patientId, patientFirstName, patientLastName, patientEmail, patientPhoneNumber,
                       patientGender, patientAge, patientAddress, patientEmergencyContact, patientStatus,
                       patientCreatedAt, patientUpdatedAt
                FROM Patient WHERE patientId = :patientId
            """;

    public static final String UPDATE_PATIENT = """
                UPDATE Patient
                SET patientFirstName = :patientFirstName,
                    patientLastName = :patientLastName,
                    patientEmail = :patientEmail,
                    patientPhoneNumber = :patientPhoneNumber,
                    patientGender = :patientGender,
                    patientAge = :patientAge,
                    patientAddress = :patientAddress,
                    patientEmergencyContact = :patientEmergencyContact,
                    patientStatus = COALESCE(:patientStatus,'ACTIVE'),
                    patientUpdatedAt = NOW()
                WHERE patientId = :patientId
            """;

    public static final String DELETE_PATIENT = """
                DELETE FROM Patient WHERE patientId = :patientId
            """;
}
