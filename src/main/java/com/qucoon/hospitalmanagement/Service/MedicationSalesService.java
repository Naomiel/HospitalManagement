package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.entity.ViewMedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationList;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesCreateRequest;
import com.qucoon.hospitalmanagement.model.request.MedicationSalesRequest;
import com.qucoon.hospitalmanagement.model.response.MedicationSalesResponse;
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

    public List<ViewMedicationSales> getAllActiveMedicationSales(){
        return medicationSalesRepository.getAllActiveMedicationSales();
    }


    public MedicationSalesResponse getMedicationSalesById(int id){
        return medicationSalesRepository.getMedicationSalesById(id);
    }

    public int createMedicationSales(MedicationSalesCreateRequest originalRequest){
        Gson gson = new Gson();

        MedicationSalesRequest request = new MedicationSalesRequest(originalRequest.getMedicationSalesPatientId(), originalRequest.getMedicationSalesStaffId());

        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
        System.out.println(medicationSales);

        List<MedicationList> list = gson.fromJson(gson.toJson(originalRequest.getMedicationIdAndQuantity()), new TypeToken<List<MedicationList>>(){}.getType());

        int medicationSalesId = medicationSalesRepository.createMedicationSales(medicationSales);

        return medicationSalesRepository.createMedicationSalesItems(medicationSalesId, list);
    }

    public int updateMedicationSales(String id, MedicationSalesCreateRequest request){
        Gson gson = new Gson();
        var medicationSales = gson.fromJson(gson.toJson(request), MedicationSales.class);
//        var Id = gson.fromJson(gson.toJson(id), String.class);
        return medicationSalesRepository.updateMedicationSales(id, medicationSales);
    }

    public int deleteMedicationSales(int id){
        return medicationSalesRepository.deleteMedicationSales(id);
    }
}
