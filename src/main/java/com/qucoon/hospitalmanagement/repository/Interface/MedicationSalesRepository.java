package com.qucoon.hospitalmanagement.repository.Interface;


import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.entity.ViewMedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationList;
import com.qucoon.hospitalmanagement.model.response.MedicationSalesResponse;

import java.util.List;

public interface MedicationSalesRepository {
    List<MedicationSales> getAllMedicationSales();

    List<ViewMedicationSales> getAllActiveMedicationSales();

    MedicationSalesResponse getMedicationSalesById(int medicationSalesId);

    int createMedicationSales(MedicationSales medicationSales);

    int createMedicationSalesItems(int medicationSalesId, List<MedicationList> medicationIdAndQuantity);

    int updateMedicationSales(String medicationSalesId, MedicationSales medicationSales);

    int deleteMedicationSales(int medicationSalesId);
}
