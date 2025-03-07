package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.MedicationSales;

import java.util.List;

public class GetAllMedicationSalesResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public List<MedicationSales> data;

    public GetAllMedicationSalesResponse(String responseCode, String responseStatus, String responseMessage, List<MedicationSales> medicationSalesResponseList)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = medicationSalesResponseList;
    }
}
