package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Medication;

public class GetMedicationSalesResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public MedicationSalesResponse data;

    public GetMedicationSalesResponse(String responseCode, String responseStatus, String responseMessage, MedicationSalesResponse medicationSalesResponse)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = medicationSalesResponse;
    }
}
