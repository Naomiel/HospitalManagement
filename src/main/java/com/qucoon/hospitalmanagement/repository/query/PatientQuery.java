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

    public static final String GET_ALL = "SELECT * FROM Patient where patientStatus = 'ACTIVE'";

    public static final String GET_BY_ID = "SELECT * FROM Patient WHERE patientId=:patientId AND patientStatus='ACTIVE'";

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

//    public static final String DELETE_PATIENT = "Update Patient set staffStatus = 'deleted' WHERE patientId = :patientId";
//
//    public static String buildUpdateQuery(Patient request, String staff_id) {
//        StringBuilder query = new StringBuilder("UPDATE patient SET ");
//        Map<String, Object> params = new HashMap<>();
//
//        if (request.getFirstName() != null) {
//            query.append("patientFirstName = :patientFirstName, ");
//            params.put("patientFirstName", request.getFirstName());
//        }
//        if (request.getLastName() != null) {
//            query.append("patientLastName = :patientLastName, ");
//            params.put("patientLastName", request.getLastName());
//        }
//
//        if (request.getEmail() != null) {
//            query.append("patientEmail = :patientEmail, ");
//            params.put("patientEmail", request.getEmail());
//        }
//        if (request.getPhoneNumber() != null) {
//            query.append("patientPhoneNumber = :patientPhoneNumber, ");
//            params.put("patientPhoneNumber", request.getPhoneNumber());
//        }
//        if (request.getGender() != null) {
//            query.append("patientGender = :patientGender, ");
//            params.put("patientGender", request.getGender());
//        }
//        if (request.getAge() != null) {
//            query.append("patientAge = :patientAge, ");
//            params.put("patientAge", request.getAge());
//        }
//        if (request.getAddress() != null) {
//            query.append("patientAddress = :patientAddress, ");
//            params.put("patientAddress", request.getAddress());
//        }
//        if (request.getEmergencyContact() != null) {
//            query.append("patientEmergencyContact = :patientEmergencyContact, ");
//            params.put("patientEmergencyContact", request.getEmergencyContact());
//        }
//
//        // Always update timestamp
//        query.append("staffUpdatedAt = CURRENT_TIMESTAMP ");
//
//        // Add WHERE condition
//        query.append("WHERE staffId = :staffId");
//        params.put("staffId", staff_id);
//
//        return query.toString();
//    }

}
