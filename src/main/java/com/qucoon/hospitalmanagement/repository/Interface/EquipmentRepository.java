package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Equipment;

import java.util.List;

public interface EquipmentRepository {
    int createEquipment(Equipment equipment);
    List<Equipment> getAllEquipment();
    Equipment getEquipmentById(int equipmentId);
    int updateEquipment(Equipment Equipment, int equipmentId);
    int deleteEquipment(int equipmentId);
}
