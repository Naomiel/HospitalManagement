package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;

import java.util.List;

public interface AppointmentRepository {
    int createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(int id);
    int updateAppointment(int id, AppointmentCreateRequest appointment);
    int deleteAppointment(int id);
    int rescheduleAppointment(int id, Appointment appointment);
    int cancelAppointment(int id);
}
