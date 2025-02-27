package com.qucoon.hospitalmanagement.repository.Interface;


import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationList;

import java.util.List;

public interface MedicationSalesRepository {
    List<MedicationSales> getAllMedicationSales();

    List<MedicationSales> getAllActiveMedicationSales();

    MedicationSales getMedicationSalesById(int medicationSalesId);

    int createMedicationSales(MedicationSales medicationSales);

    int createMedicationSalesItems(int medicationSalesId, List<MedicationList> medicationIds);

    int updateMedicationSales(String medicationSalesId, MedicationSales medicationSales);

    int deleteMedicationSales(int medicationSalesId);
}
