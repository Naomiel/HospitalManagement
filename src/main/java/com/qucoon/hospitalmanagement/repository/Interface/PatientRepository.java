package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.request.PatientCreateRequest;
import java.util.List;


public interface PatientRepository {
    List<Patient> getAllPatients();

    Patient getPatientById(int patientId);

    int createPatient(PatientCreateRequest patient);

    int updatePatient(int patientId, PatientCreateRequest patient);

    int deletePatient(int patientId);
}
