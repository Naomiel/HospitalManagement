package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.entity.ViewMedicationSales;

import java.util.List;

public class GetAllMedicationSalesViewResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public List<ViewMedicationSales> data;

    public GetAllMedicationSalesViewResponse(String responseCode, String responseStatus, String responseMessage, List<ViewMedicationSales> medicationSalesResponseList)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = medicationSalesResponseList;
    }
}
