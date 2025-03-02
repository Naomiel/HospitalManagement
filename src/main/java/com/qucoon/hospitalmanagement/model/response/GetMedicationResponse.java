package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Medication;

public class GetMedicationResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public Medication data;

    public GetMedicationResponse(String responseCode, String responseStatus, String responseMessage, Medication medication)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = medication;
    }
}
