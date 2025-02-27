package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.ViewMedicationItems;
import com.qucoon.hospitalmanagement.model.entity.ViewMedicationSales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class MedicationSalesResponse {
    private ViewMedicationSales medicationSales;
    private List<ViewMedicationItems> medicationItems;

    public MedicationSalesResponse(ViewMedicationSales medicationSales, List<ViewMedicationItems> medicationItems) {
        this.medicationSales = medicationSales;
        this.medicationItems = medicationItems;
    }
}
