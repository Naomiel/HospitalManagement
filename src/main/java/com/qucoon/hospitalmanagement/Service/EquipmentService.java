package com.qucoon.hospitalmanagement.Service;


import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.request.EquipmentCreateRequest;
import com.qucoon.hospitalmanagement.model.request.EquipmentUpdateRequest;
import com.qucoon.hospitalmanagement.model.response.*;
import com.qucoon.hospitalmanagement.model.response.EquipmentResponse;
import com.qucoon.hospitalmanagement.model.response.EquipmentResponse;
import com.qucoon.hospitalmanagement.model.response.EquipmentResponse;
import com.qucoon.hospitalmanagement.repository.Interface.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public EquipmentResponse createEquipment(EquipmentCreateRequest equipmentCreateRequest) {
        Gson gson = new Gson();
        var request = gson.fromJson(gson.toJson(equipmentCreateRequest), Equipment.class);
        try
        {
            var equipment = equipmentRepository.createEquipment(request);
            List<Equipment> equipment_list = new ArrayList<>();
            if (equipment != null) {
                equipment_list.add(equipment);
            }
            return new EquipmentResponse("00","successful","Equipment created succesfully", equipment_list);
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new EquipmentResponse("09","failed","Unable to create equipment", new ArrayList<Equipment>() );
        }
    }

    public EquipmentResponse getAllEquipment(){
        var equipment =  equipmentRepository.getAllEquipment();
        return new EquipmentResponse("00","successful","Equipment fetched succesfully", equipment);
    }

    public EquipmentResponse getEquipmentById(int equipmentId) {
        try
        {
            var equipment = equipmentRepository.getEquipmentById(equipmentId);
            List<Equipment> equipment_list = new ArrayList<>();
            if (equipment != null) {
                equipment_list.add(equipment);
            }
            return new EquipmentResponse("00","successful","Equipment fetched succesfully", equipment_list);
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new EquipmentResponse("09","failed","Unable to find equipment", new ArrayList<Equipment>() );        }
    }

    public EquipmentResponse updateEquipment(EquipmentUpdateRequest equipmentUpdateRequest, int equipmentId) {
        try{
            Gson gson = new Gson();
            var equipment = gson.fromJson(gson.toJson(equipmentUpdateRequest), Equipment.class);
            var updateEquipment = equipmentRepository.updateEquipment(equipment, equipmentId);
            if(updateEquipment == 1) {
                List<Equipment> equipment_list = new ArrayList<>();
                var equipmentInstance = equipmentRepository.getEquipmentById(equipmentId);
                if (equipmentInstance != null) {
                    equipment_list.add(equipmentInstance);
                }
                return new EquipmentResponse("00","successful","Equipment updated succesfully", equipment_list);
            }
        }
        catch(Exception ex){
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new EquipmentResponse("09","failed","Unable to update equipment", new ArrayList<Equipment>() );
        }
        return new EquipmentResponse("09","failed", "Unable to update equipment", new ArrayList<Equipment>());

    }
    
    public EquipmentResponse deleteEquipment(int equipmentId) {

        try
        {
            var equipment = equipmentRepository.deleteEquipment(equipmentId);
            if(equipment > 0) return new EquipmentResponse("00","successful", "Equipment deleted succesfully", new ArrayList<Equipment>());
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new EquipmentResponse("09","failed","Unable to delete equipment", new ArrayList<Equipment>() );
        }
        return new EquipmentResponse("09","failed","Unable to delete equipment", new ArrayList<Equipment>() );
    }

}
