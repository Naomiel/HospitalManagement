package com.qucoon.hospitalmanagement.service;


import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import com.qucoon.hospitalmanagement.model.response.AppResponse;
import com.qucoon.hospitalmanagement.model.response.PatientResponse;
import com.qucoon.hospitalmanagement.repository.Interface.PatientRepository;
import com.qucoon.hospitalmanagement.system.OrikaBeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final OrikaBeanMapper mapper;

    private static final String FAILED = "failed";
    private static final String SUCCESS = "successful";


    public AppResponse<List<PatientResponse>> getAllPatient() {
        try {
            List<Patient> patientList = patientRepository.getAllPatients();
            if (patientList.isEmpty()) {
                return new AppResponse<>("00", SUCCESS, "", Collections.emptyList());
            }
            List<PatientResponse> patientResponseList = new ArrayList<>();
            for (Patient patient : patientList) {
                patientResponseList.add(this.mapper.map(patient, PatientResponse.class));
            }
            return new AppResponse<>("00", SUCCESS, "", patientResponseList);
        } catch (Exception e) {
            log.error("Error getting all patients {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "", null);
    }

    public AppResponse<PatientResponse> getPatientById(int id) {
        Gson gson = new Gson();
        try {
            Patient patient = patientRepository.getPatientById(id);

            if (patient != null) {
                var patientResp = gson.fromJson(gson.toJson(patient), PatientResponse.class);
                return new AppResponse<>("00", SUCCESS, "Patient retrieved successfully", patientResp);
            } else {
                return new AppResponse<>("09", FAILED, "Patient not found", null);
            }
        } catch (Exception e) {
            log.error("Error retrieving patient: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to retrieve patient", null);
    }


    public AppResponse<PatientResponse> createPatient(PatientCreateRequest request) {
        Gson gson = new Gson();
        try {
            var patientId = patientRepository.createPatient(request);

            if (patientId > 0) {
                Patient patient1 = patientRepository.getPatientById(patientId);
                var patientResp = gson.fromJson(gson.toJson(patient1), PatientResponse.class);
                return new AppResponse<>("00", SUCCESS, "Patient created successfully", patientResp);
            } else {
                return new AppResponse<>("09", FAILED, "Unable to create patient", null);
            }
        } catch (Exception e) {
            log.error("Error creating patient: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to create patient", null);
    }

    public AppResponse<PatientResponse> updatePatient(int patientId, PatientCreateRequest request) {
        Gson gson = new Gson();
        try {
            int rowsUpdated = patientRepository.updatePatient(patientId, request);

            if (rowsUpdated > 0) {
                Patient updatedPatient = patientRepository.getPatientById(patientId);
                var patientResp = gson.fromJson(gson.toJson(updatedPatient), PatientResponse.class);
                return new AppResponse<>("00", SUCCESS, "Patient updated successfully", patientResp);
            } else {
                return new AppResponse<>("09", FAILED, "Unable to update patient", null);
            }
        } catch (Exception e) {
            log.error("Error updating patient: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to update patient", null);
    }


    public AppResponse<Void> deletePatient(int patientId) {
        try {
            int rowsDeleted = patientRepository.deletePatient(patientId);

            if (rowsDeleted > 0) {
                return new AppResponse<>("00", SUCCESS, "Patient deleted successfully", null);
            } else {
                return new AppResponse<>("09", FAILED, "Patient not found or already deleted", null);
            }
        } catch (Exception e) {
            log.error("Error deleting patient: {}", e.getMessage(), e);
        }
        return new AppResponse<>("09", FAILED, "Unable to delete patient", null);
    }
}
