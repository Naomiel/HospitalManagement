package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Medication;

import java.util.List;

public class GetAllMedicationsResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public List<Medication> data;

    public GetAllMedicationsResponse(String responseCode, String responseStatus, String responseMessage, List<Medication> medicationList)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = medicationList;
    }
}
