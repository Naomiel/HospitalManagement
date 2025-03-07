package com.qucoon.hospitalmanagement.model.response;

public class GetMedicationRevenueResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public MedicationRevenueResp medicationRevenue;

    public GetMedicationRevenueResponse(String responseCode, String responseStatus, String responseMessage, MedicationRevenueResp medicationRevenue)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.medicationRevenue = medicationRevenue;
    }
}
