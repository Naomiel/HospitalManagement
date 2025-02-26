package com.qucoon.hospitalmanagement.Service;


import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.request.EquipmentCreateRequest;
import com.qucoon.hospitalmanagement.model.request.EquipmentUpdateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public int createEquipment(EquipmentCreateRequest equipmentCreateRequest) {
        Gson gson = new Gson();
        var equipment = gson.fromJson(gson.toJson(equipmentCreateRequest), Equipment.class);
        return equipmentRepository.createEquipment(equipment);
    }

    public List<Equipment> getAllEquipment(){
        return equipmentRepository.getAllEquipment();
    }

    public Equipment getEquipmentById(int equipmentId) {
        try
        {
            return equipmentRepository.getEquipmentById(equipmentId);
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());

            return null;
        }
    }

    public int updateEquipment(EquipmentUpdateRequest equipmentUpdateRequest, int equipmentId) {
        Gson gson = new Gson();
        var equipment = gson.fromJson(gson.toJson(equipmentUpdateRequest), Equipment.class);
        return equipmentRepository.updateEquipment(equipment, equipmentId);
    }
    public int deleteEquipment(int equipmentId) {
        return equipmentRepository.deleteEquipment(equipmentId);
    }

}
