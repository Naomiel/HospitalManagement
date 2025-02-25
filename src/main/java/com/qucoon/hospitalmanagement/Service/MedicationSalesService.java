package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
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

    public List<MedicationSales> getAllMedicationSales(){
        return medicationSalesRepository.getAllMedicationSales();
    }

    public List<MedicationSales> getAllActiveMedicationSales(){
        return medicationSalesRepository.getAllActiveMedicationSales();
    }


    public MedicationSales getMedicationSalesById(int id){
        return medicationSalesRepository.getMedicationSalesById(id);
    }

    public int createMedicationSales(MedicationSalesCreateRequest request){
        Gson gson = new Gson();
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        return medicationSalesRepository.createMedicationSales(medicationSales);
    }

    public int updateMedicationSales(String id, MedicationSalesCreateRequest request){
        Gson gson = new Gson();
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        return medicationSalesRepository.updateMedicationSales(Id, medicationSales);
    }

    public int deleteMedicationSales(int id){
        return medicationSalesRepository.deleteMedicationSales(id);
    }
}
