package com.qucoon.hospitalmanagement.repository.query;

public class AppointmentQuery {
    public static final String INSERT_APPOINTMENT = """
        INSERT INTO appointments (patient_id, doctor_id, appointment_date, status, reason, created_at)
        VALUES (:patientId, :doctorId, :appointmentDate, 'SCHEDULED', :reason, CURRENT_TIMESTAMP)
    """;

    public static final String GET_ALL_APPOINTMENTS = """
        SELECT * FROM appointments
    """;

    public static final String GET_APPOINTMENT_BY_ID = """
        SELECT * FROM appointments WHERE id = :id
    """;

    public static final String UPDATE_APPOINTMENT = """
        UPDATE appointments
        SET appointment_date = :appointmentDate, updated_at = CURRENT_TIMESTAMP
        WHERE id = :id
    """;

    public static final String DELETE_APPOINTMENT = """
        DELETE FROM appointments WHERE id = :id
    """;

    public static final String RESCHEDULE_APPOINTMENT = """
        UPDATE appointments
        SET appointment_date = :appointmentDate, updated_at = CURRENT_TIMESTAMP
        WHERE id = :id
    """;

    public static final String CANCEL_APPOINTMENT = """
        UPDATE appointments
        SET status = 'CANCELLED', updated_at = CURRENT_TIMESTAMP
        WHERE id = :id
    """;
}
