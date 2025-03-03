package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.model.response.EquipmentResponse;

import java.util.List;

public interface EquipmentRepository {
    Equipment createEquipment(Equipment equipment);
    List<Equipment> getAllEquipment();
    Equipment getEquipmentById(int equipmentId);
    int updateEquipment(Equipment Equipment, int equipmentId);
    int deleteEquipment(int equipmentId);
}
