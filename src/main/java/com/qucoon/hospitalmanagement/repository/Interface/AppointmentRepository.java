package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.entity.ViewAppointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;

import java.util.List;

public interface AppointmentRepository {
    int createAppointment(AppointmentCreateRequest appointment);
    List<ViewAppointment> getAllAppointments();
    ViewAppointment getAppointmentById(int id);
    int updateAppointment(int id, AppointmentCreateRequest appointment);
    int deleteAppointment(int id);
    int rescheduleAppointment(int id, Appointment appointment);
    int cancelAppointment(int id);
}
