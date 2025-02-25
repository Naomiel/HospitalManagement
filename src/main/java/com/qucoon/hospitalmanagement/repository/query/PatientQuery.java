package com.qucoon.hospitalmanagement.repository.query;

public class PatientQuery {
    public static final String INSERT_PATIENT = """
        INSERT INTO Patient (patientFirstName, patientLastName, patientEmail, patientPhoneNumber, patientGender, patientCreatedAt, patientUpdatedAt)
        VALUES (:patientFirstName, :patientLastName, :patientEmail, :patientPhoneNumber, :patientGender, NOW(), NOW())
    """;

    public static final String GET_ALL = """
        SELECT * FROM Patient
    """;

    public static final String GET_BY_ID = """
        SELECT * FROM Patient WHERE patientId = :patientId
    """;

    public static final String UPDATE_PATIENT = """
        UPDATE Patient
        SET patientFirstName = :patientFirstName,
            patientLastName = :patientLastName,
            patientEmail = :patientEmail,
            patientPhoneNumber = :patientPhoneNumber,
            patientGender = :patientGender,
            patientAddress = :patientAddress,
            patientEmergencyContact = :patientEmergencyContact,
            updatedAt = NOW()
        WHERE patientId = :patientId
    """;

    public static final String DELETE_PATIENT = """
        DELETE FROM Patient WHERE patientId = :patientId
    """;
}
