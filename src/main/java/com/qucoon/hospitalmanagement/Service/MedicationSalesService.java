package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationList;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesRequest;
import com.qucoon.hospitalmanagement.model.response.*;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationSalesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationSalesService {
    private final MedicationSalesRepository medicationSalesRepository;
    private static final Logger logger = LoggerFactory.getLogger(MedicationSalesService.class);


    @Autowired
    public MedicationSalesService(MedicationSalesRepository medicationSalesRepository) {
        this.medicationSalesRepository = medicationSalesRepository;
    }

    public GetAllMedicationSalesResponse getAllMedicationSales(){
        try {
            var resp = medicationSalesRepository.getAllMedicationSales();
            return new GetAllMedicationSalesResponse("00", "success", "MedicationSales Fetched successfully", resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetAllMedicationSalesResponse("106", "failed", "Internal Server Error", null);
        }

    }

    public GetAllMedicationSalesViewResponse getAllActiveMedicationSales(){
        try {
            var resp = medicationSalesRepository.getAllActiveMedicationSales();
            return new GetAllMedicationSalesViewResponse("00", "success", "MedicationSales Fetched successfully", resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetAllMedicationSalesViewResponse("106", "failed", "Internal Server Error", null);
        }
    }


    public GetMedicationSalesResponse getMedicationSalesById(int id){
        try {
            var resp = medicationSalesRepository.getMedicationSalesById(id);
            return new GetMedicationSalesResponse("00", "success", "MedicationSales Fetched successfully", resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetMedicationSalesResponse("106", "failed", "Internal Server Error", null);
        }
    }

    public GenericResponse createMedicationSales(MedicationSalesCreateRequest originalRequest){
        Gson gson = new Gson();
        MedicationSalesRequest request = new MedicationSalesRequest(originalRequest.getMedicationSalesPatientId(), originalRequest.getMedicationSalesStaffId());
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        List<MedicationList> list = gson.fromJson(gson.toJson(originalRequest.getMedicationIdAndQuantity()), new TypeToken<List<MedicationList>>(){}.getType());

        try {
            var quantityNotInStocklist = medicationSalesRepository.checkMedicationQuantity(list);
            if (quantityNotInStocklist.isEmpty()) {
                int medicationSalesId = medicationSalesRepository.createMedicationSales(medicationSales);
                if (medicationSalesId == -1) {
                    return new GenericResponse("106", "failed", "Failed to create MedicationSales record. Please Enter valid Request body and ensure that all required fields are inputed");
                }
                var createMedicationSalesItem = medicationSalesRepository.createMedicationSalesItems(medicationSalesId, list);
                if (createMedicationSalesItem > 0) {
                    return new GenericResponse("00", "success", "MedicationSales Created Successfully");
                } else {
                    medicationSalesRepository.deleteMedicationSales(medicationSalesId);
                    return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that the list is not empty and all required fields are inputed");
                }
            }
            return new GenericResponse("106", "failed", String.join(", ", quantityNotInStocklist));
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }

    public GenericResponse updateMedicationSales(String id, MedicationSalesRequest request){
        Gson gson = new Gson();
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        try {
            var resp =  medicationSalesRepository.updateMedicationSales(Id, medicationSales);
            if (resp > 0){
                return new GenericResponse("00", "success", "MedicationSales Updated Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that all required fields are inputed");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }

    public GenericResponse deleteMedicationSales(int id){
        try {
            var resp =  medicationSalesRepository.deleteMedicationSales(id);
            return new GenericResponse("00", "success", "MedicationSales deleted Successfully");
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }
}
