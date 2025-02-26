package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Medication;

import java.util.List;

public interface MedicationRepository {
    List<Medication> getAllMedications();

    List<Medication> getAllActiveMedications();

    Medication getMedicationById(int medicationId);

    int createMedication(Medication medication);

    int updateMedication(String medicationId, Medication medication);

    int deleteMedication(int medicationId);
}
