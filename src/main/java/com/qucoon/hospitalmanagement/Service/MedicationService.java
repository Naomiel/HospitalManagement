package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
import com.qucoon.hospitalmanagement.model.response.GenericResponse;
import com.qucoon.hospitalmanagement.model.response.GetAllMedicationsResponse;
import com.qucoon.hospitalmanagement.model.response.GetMedicationResponse;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private static final Logger logger = LoggerFactory.getLogger(MedicationService.class);


    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public GetAllMedicationsResponse getAllMedication(){
        try {
            var resp = medicationRepository.getAllMedications();
            return new GetAllMedicationsResponse("00", "success", "Medications Fetched Successfully",resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetAllMedicationsResponse("106", "failed", "Internal Server Error", null);
        }
    }

    public GetAllMedicationsResponse getAllActiveMedication(){
        try {
            var resp = medicationRepository.getAllActiveMedications();
            return new GetAllMedicationsResponse("00", "success", "Medications Fetched Successfully",resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetAllMedicationsResponse("106", "failed", "Internal Server Error", null);
        }
    }


    public GetMedicationResponse getMedicationById(int id){
        try {
            var resp = medicationRepository.getMedicationById(id);
            if (resp == null) {
                return new GetMedicationResponse("00", "failed", "Medication Not Found or has been deleted", null);
            }
            return new GetMedicationResponse("00", "success", "Medication Fetched Successfully",resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetMedicationResponse("106", "failed", "Internal Server Error", null);
        }
    }

    public GenericResponse createMedication(MedicationAddRequest request){
        Gson gson = new Gson();
        var medication = gson.fromJson(gson.toJson(request), Medication.class);
        try {
            var resp =  medicationRepository.createMedication(medication);
            if (resp > 0){
                return new GenericResponse("00", "success", "Medication Created Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that all required fields are inputed");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }

    public GenericResponse updateMedication(String id, MedicationAddRequest request){
        Gson gson = new Gson();
        var medication = gson.fromJson(gson.toJson(request), Medication.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        try {
            var resp =  medicationRepository.updateMedication(id, medication);
            if (resp > 0){
                return new GenericResponse("00", "success", "Medication updated Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter a valid Request body and ensure that medication id exists");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }

    public GenericResponse deleteMedication(int id){
        try {
            var resp =  medicationRepository.deleteMedication(id);
            if (resp > 0){
                return new GenericResponse("00", "success", "Medication deleted Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that medication id exist");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }
}
