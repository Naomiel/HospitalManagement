package com.qucoon.hospitalmanagement.service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.entity.ViewAppointment;
import com.qucoon.hospitalmanagement.model.request.AppointmentCreateRequest;
import com.qucoon.hospitalmanagement.model.response.AppResponse;
import com.qucoon.hospitalmanagement.model.response.AppointmentResponse;
import com.qucoon.hospitalmanagement.repository.Interface.AppointmentRepository;
import com.qucoon.hospitalmanagement.system.OrikaBeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final OrikaBeanMapper mapper;


    private static final String FAILED = "failed";
    private static final String SUCCESS = "successful";


    public AppResponse<AppointmentResponse> createAppointment(AppointmentCreateRequest request) {
        Gson gson = new Gson();
        try {
            int appointmentId = appointmentRepository.createAppointment(request);

            if (appointmentId > 0) {
                ViewAppointment appointment = appointmentRepository.getAppointmentById(appointmentId);
                var appointmentResp = gson.fromJson(gson.toJson(appointment), AppointmentResponse.class);
                return new AppResponse<>("00", SUCCESS, "Appointment created successfully", appointmentResp);
            } else {
                return new AppResponse<>("09", FAILED, "Unable to create appointment", null);
            }
        } catch (Exception e) {
            log.error("Error creating appointment: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to create appointment", null);
    }


    public AppResponse<List<ViewAppointment>> getAllAppointments() {
            try {
                List<ViewAppointment> appointmentList = appointmentRepository.getAllAppointments();

                if (appointmentList.isEmpty()) {
                    return new AppResponse<>("00", SUCCESS, "No appointments found", Collections.emptyList());
                }

                return new AppResponse<>("00", SUCCESS, "Appointments fetched successfully", appointmentList);
            } catch (Exception e) {
                log.error("Error getting all appointments: {}", e.getMessage(), e);
            }
            return new AppResponse<>("09", FAILED, "Unable to fetch appointments", null);
        }


    public AppResponse<ViewAppointment> getAppointmentById(int id) {
        try {
            ViewAppointment appointment = appointmentRepository.getAppointmentById(id);

            if (appointment != null) {
                return new AppResponse<>("00", SUCCESS, "Appointment retrieved successfully", appointment);
            } else {
                return new AppResponse<>("09", FAILED, "Appointment not found", null);
            }
        } catch (Exception e) {
            log.error("Error retrieving appointment: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to retrieve appointment", null);
    }


    public AppResponse<AppointmentResponse> updateAppointment(int appointmentId, AppointmentCreateRequest request) {
        Gson gson = new Gson();
        try {
            int rowsUpdated = appointmentRepository.updateAppointment(appointmentId, request);

            if (rowsUpdated > 0) {
                ViewAppointment updatedAppointment = appointmentRepository.getAppointmentById(appointmentId);
                var appointmentResp = gson.fromJson(gson.toJson(updatedAppointment), AppointmentResponse.class);
                return new AppResponse<>("00", SUCCESS, "Appointment updated successfully", appointmentResp);
            } else {
                return new AppResponse<>("09", FAILED, "Unable to update appointment", null);
            }
        } catch (Exception e) {
            log.error("Error updating appointment: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to update appointment", null);
    }

    public AppResponse<Void> deleteAppointment(int appointmentId) {
        try {
            int rowsDeleted = appointmentRepository.deleteAppointment(appointmentId);

            if (rowsDeleted > 0) {
                return new AppResponse<>("00", SUCCESS, "Appointment deleted successfully", null);
            } else {
                return new AppResponse<>("09", FAILED, "Appointment not found or already deleted", null);
            }
        } catch (Exception e) {
            log.error("Error deleting appointment: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to delete appointment", null);
    }

    public AppResponse<AppointmentResponse> rescheduleAppointment(int appointmentId, AppointmentCreateRequest request) {
        Gson gson = new Gson();
        try {
            int rowsUpdated = appointmentRepository.updateAppointment(appointmentId, request);

            if (rowsUpdated > 0) {
                ViewAppointment updatedAppointment = appointmentRepository.getAppointmentById(appointmentId);
                var appointmentResp = gson.fromJson(gson.toJson(updatedAppointment), AppointmentResponse.class);
                return new AppResponse<>("00", SUCCESS, "Appointment rescheduled successfully", appointmentResp);
            } else {
                return new AppResponse<>("09", FAILED, "Unable to reschedule appointment", null);
            }
        } catch (Exception e) {
            log.error("Error rescheduling appointment: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to reschedule appointment", null);
    }


    public AppResponse<Void> cancelAppointment(int appointmentId) {
        try {
            int rowsUpdated = appointmentRepository.cancelAppointment(appointmentId);

            if (rowsUpdated > 0) {
                return new AppResponse<>("00", SUCCESS, "Appointment canceled successfully", null);
            } else {
                return new AppResponse<>("09", FAILED, "Appointment not found or already canceled", null);
            }
        } catch (Exception e) {
            log.error("Error canceling appointment: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to cancel appointment", null);
    }

}
