package com.qucoon.hospitalmanagement.model.entity;

import com.qucoon.hospitalmanagement.model.request.MedicationList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewMedicationSales {
    private int medicationSalesId;
    private String patientFirstName;
    private String patientLastName;
    private int patientAge;
    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
}
