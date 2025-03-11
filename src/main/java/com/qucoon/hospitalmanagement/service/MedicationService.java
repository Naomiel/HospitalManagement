package com.qucoon.hospitalmanagement.service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
import com.qucoon.hospitalmanagement.model.response.*;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;


    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public AppResponse<List<Medication>> getAllMedication(){
        try {
            var resp = medicationRepository.getAllMedications();
            return new AppResponse<>("00", "success", "Medications Fetched Successfully",resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<List<Medication>> getAllActiveMedication(){
        try {
            var resp = medicationRepository.getAllActiveMedications();
            return new AppResponse<>("00", "success", "Medications Fetched Successfully",resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }


    public AppResponse<Medication> getMedicationById(int id){
        try {
            var resp = medicationRepository.getMedicationById(id);
            if (resp == null) {
                return new AppResponse<>("00", "failed", "Medication Not Found or has been deleted", null);
            }
            return new AppResponse<>("00", "success", "Medication Fetched Successfully",resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<MedicationRevenueResp> getMedicationRevenueByIdAndDate(int medicationId, String startDate, String endDate) {
        try {
            var resp = medicationRepository.getMedicationRevenueByIdAndDate(medicationId, startDate, endDate);
            if (resp == 0.00) {
                return new AppResponse<>("00", "success", "This medication has not generated any revenue so far", null);
            }
            return new AppResponse<>("00", "success", "This medication has generated " + resp + " revenue so far", new MedicationRevenueResp(resp));
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> createMedication(MedicationAddRequest request){
        Gson gson = new Gson();
        var medication = gson.fromJson(gson.toJson(request), Medication.class);
        try {
            var resp =  medicationRepository.createMedication(medication);
            if (resp > 0){
                return new AppResponse<>("00", "success", "Medication Created Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that all required fields are inputed", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> updateMedication(String id, MedicationAddRequest request){
        Gson gson = new Gson();
        var medication = gson.fromJson(gson.toJson(request), Medication.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        try {
            var resp =  medicationRepository.updateMedication(id, medication);
            if (resp > 0){
                return new AppResponse<>("00", "success", "Medication updated Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter a valid Request body and ensure that medication id exists", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> deleteMedication(int id){
        try {
            var resp =  medicationRepository.deleteMedication(id);
            if (resp > 0){
                return new AppResponse<>("00", "success", "Medication deleted Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that medication id exist", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }
}
