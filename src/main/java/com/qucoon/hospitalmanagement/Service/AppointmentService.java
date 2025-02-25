package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.AppointmentRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    public int createAppointment(AppointmentCreateRequest request) {
        Gson gson = new Gson();
        var patient = gson.fromJson(gson.toJson(request), Appointment.class);
        return appointmentRepository.createAppointment(patient);

    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public int updateAppointment(int id, AppointmentCreateRequest request) {
        return appointmentRepository.updateAppointment(id, request);
    }

    public int deleteAppointment(int id) {
        return appointmentRepository.deleteAppointment(id);
    }

    public int rescheduleAppointment(int id, Appointment appointment) {
        return appointmentRepository.rescheduleAppointment(id, appointment);
    }

    public int cancelAppointment(int id) {
        return appointmentRepository.cancelAppointment(id);
    }
}
