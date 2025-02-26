package com.qucoon.hospitalmanagement.Service;


import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import com.qucoon.hospitalmanagement.model.response.PatientResponse;
import com.qucoon.hospitalmanagement.repository.Interface.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<Patient> getAllPatient() {
        return patientRepository.getAllPatients();
    }


    public Patient getPatientById(int id){
        return patientRepository.getPatientById(id);
    }


    public PatientResponse createPatient(PatientCreateRequest request) {
        Gson gson = new Gson();
        var patient = gson.fromJson(gson.toJson(request), Patient.class);
        int patientId = patientRepository.createPatient(patient);
        if(patientId > 0){
          Patient patient1 = patientRepository.getPatientById(patientId);
          var patientResp = gson.fromJson(gson.toJson(patient1), PatientResponse.class);
          return patientResp;
        }
        return new PatientResponse();
    }

    public int updatePatient(int patientId, Patient patient) {
        return patientRepository.updatePatient(patientId, patient);
    }

    public int deletePatient(int patientId) {
        return patientRepository.deletePatient(patientId);
    }
}
