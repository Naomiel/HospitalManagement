package com.qucoon.hospitalmanagement.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.entity.ViewMedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationList;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesRequest;
import com.qucoon.hospitalmanagement.model.response.*;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationSalesService {
    private final MedicationSalesRepository medicationSalesRepository;


    @Autowired
    public MedicationSalesService(MedicationSalesRepository medicationSalesRepository) {
        this.medicationSalesRepository = medicationSalesRepository;
    }

    public AppResponse<List<MedicationSales>> getAllMedicationSales(){
        try {
            var resp = medicationSalesRepository.getAllMedicationSales();
            return new AppResponse<>("00", "success", "MedicationSales Fetched successfully", resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }

    }

    public AppResponse<List<ViewMedicationSales>> getAllActiveMedicationSales(){
        try {
            var resp = medicationSalesRepository.getAllActiveMedicationSales();
            return new AppResponse<>("00", "success", "MedicationSales Fetched successfully", resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }


    public AppResponse<MedicationSalesResponse> getMedicationSalesById(int id){
        try {
            var resp = medicationSalesRepository.getMedicationSalesById(id);
            return new AppResponse<>("00", "success", "MedicationSales Fetched successfully", resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> createMedicationSales(MedicationSalesCreateRequest originalRequest){
        Gson gson = new Gson();
        MedicationSalesRequest request = new MedicationSalesRequest(originalRequest.getMedicationSalesPatientId(), originalRequest.getMedicationSalesStaffId());
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        List<MedicationList> list = gson.fromJson(gson.toJson(originalRequest.getMedicationIdAndQuantity()), new TypeToken<List<MedicationList>>(){}.getType());

        try {
            var quantityNotInStocklist = medicationSalesRepository.checkMedicationQuantity(list);
            if (quantityNotInStocklist.isEmpty()) {
                int medicationSalesId = medicationSalesRepository.createMedicationSales(medicationSales);
                if (medicationSalesId == -1) {
                    return new AppResponse<>("106", "failed", "Failed to create MedicationSales record. Please Enter valid Request body and ensure that all required fields are inputed", null);
                }
                var createMedicationSalesItem = medicationSalesRepository.createMedicationSalesItems(medicationSalesId, list);
                if (createMedicationSalesItem > 0) {
                    return new AppResponse<>("00", "success", "MedicationSales Created Successfully", null);
                } else {
                    medicationSalesRepository.deleteMedicationSales(medicationSalesId);
                    return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that the list is not empty and all required fields are inputed", null);
                }
            }
            return new AppResponse<>("106", "failed", String.join(", ", quantityNotInStocklist), null);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> updateMedicationSales(String id, MedicationSalesRequest request){
        Gson gson = new Gson();
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        try {
            var resp =  medicationSalesRepository.updateMedicationSales(Id, medicationSales);
            if (resp > 0){
                return new AppResponse<>("00", "success", "MedicationSales Updated Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that all required fields are inputed", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> deleteMedicationSales(int id){
        try {
            var resp =  medicationSalesRepository.deleteMedicationSales(id);
            return new AppResponse<>("00", "success", "MedicationSales deleted Successfully", null);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }
}
