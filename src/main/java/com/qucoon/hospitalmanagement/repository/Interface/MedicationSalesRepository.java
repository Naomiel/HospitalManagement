package com.qucoon.hospitalmanagement.repository.Interface;


import com.qucoon.hospitalmanagement.model.entity.MedicationSales;

import java.util.List;

public interface MedicationSalesRepository {
    List<MedicationSales> getAllMedicationSales();

    List<MedicationSales> getAllActiveMedicationSales();

    MedicationSales getMedicationSalesById(int medicationSalesId);

    int createMedicationSales(MedicationSales medicationSales);

    int updateMedicationSales(String medicationSalesId, MedicationSales medicationSales);

    int deleteMedicationSales(int medicationSalesId);
}
