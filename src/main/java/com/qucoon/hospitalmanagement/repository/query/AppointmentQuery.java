package com.qucoon.hospitalmanagement.repository.query;


public class AppointmentQuery {
    public static final String INSERT_APPOINTMENT = """
        INSERT INTO Appointment (appointmentPatientId, appointmentStaffId, appointmentDate, appointmentStatus, appointmentCreatedAt)
        VALUES (:appointmentPatientId, :appointmentStaffId, :appointmentDate, 'ACTIVE', CURRENT_TIMESTAMP)
    """;

    public static final String GET_ALL_APPOINTMENTS = """
        select Appointment.*, Patient.*,Staff.* from Appointment join Patient on Appointment.appointmentPatientId = Patient.patientId
        join Staff on Appointment.appointmentStaffId = Staff.staffId
    """;
    public static final String GET_APPOINTMENT_BY_ID = """
        select Appointment.*, Patient.*,Staff.* from Appointment join Patient on Appointment.appointmentPatientId = Patient.patientId
        join Staff on Appointment.appointmentStaffId = Staff.staffId
        where Appointment.appointmentId = :appointmentId
    """;

//    public static final String UPDATE_APPOINTMENT = "SELECT * FROM Patient WHERE patientId=:patientId AND patientStatus='ACTIVE'";
public static final String UPDATE_APPOINTMENT = """
        UPDATE Appointment
        SET appointmentPatientId = :appointmentPatientId, appointmentStaffId = :appointmentStaffId, appointmentDate = :appointmentDate, appointmentUpdatedAt = CURRENT_TIMESTAMP
        WHERE appointmentId = :appointmentId
    """;

    public static final String DELETE_APPOINTMENT = """
        DELETE FROM Appointment WHERE appointmentId = :appointmentId
    """;

    public static final String RESCHEDULE_APPOINTMENT = """
        UPDATE Appointment
        SET appointmentDate = :appointmentDate, appointmentUpdatedAt = CURRENT_TIMESTAMP
        WHERE appointmentId = :appointmentId
    """;

    public static final String CANCEL_APPOINTMENT = """
        UPDATE Appointment
        SET appointmentStatus = 'CANCELLED', appointmentUpdatedAt = CURRENT_TIMESTAMP
        WHERE appointmentId = :appointmentId
    """;


}
