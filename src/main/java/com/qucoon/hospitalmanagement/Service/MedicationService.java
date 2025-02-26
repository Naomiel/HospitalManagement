package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Medication;
import com.qucoon.hospitalmanagement.model.request.MedicationAddRequest;
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

    public List<Medication> getAllMedication(){
        return medicationRepository.getAllMedications();
    }

    public List<Medication> getAllActiveMedication(){
        return medicationRepository.getAllActiveMedications();
    }


    public Medication getMedicationById(int id){
        return medicationRepository.getMedicationById(id);
    }

    public int createMedication(MedicationAddRequest request){
        Gson gson = new Gson();
        var medication = gson.fromJson(gson.toJson(request), Medication.class);
        return medicationRepository.createMedication(medication);
    }

    public int updateMedication(String id, MedicationAddRequest request){
        Gson gson = new Gson();
        var medication = gson.fromJson(gson.toJson(request), Medication.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        return medicationRepository.updateMedication(Id, medication);
    }

    public int deleteMedication(int id){
        return medicationRepository.deleteMedication(id);
    }
}
