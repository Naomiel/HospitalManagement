package com.qucoon.hospitalmanagement.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationRevenue {
    private int medicationId;
    private String startDate;
    private String endDate;
}
