package com.qucoon.hospitalmanagement.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationRevenueResp {
    Double medicationRevenue;

    public MedicationRevenueResp() {}

    public MedicationRevenueResp(Double medicationRevenue) {
        this.medicationRevenue = medicationRevenue;
    }
}
