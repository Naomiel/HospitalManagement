package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Medication;

import java.util.List;

public class GetAllMedicationSalesResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public List<MedicationSalesResponse> data;

    public GetAllMedicationSalesResponse(String responseCode, String responseStatus, String responseMessage, List<MedicationSalesResponse> medicationSalesResponseList)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = medicationSalesResponseList;
    }
}
