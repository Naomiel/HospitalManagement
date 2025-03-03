package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.entity.Medication;

public class GetItemResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public Item data;

    public GetItemResponse(String responseCode, String responseStatus, String responseMessage, Item item)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = item;
    }
}
