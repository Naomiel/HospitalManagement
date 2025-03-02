package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.entity.Medication;

import java.util.List;

public class GetAllItemsResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public List<Item> data;

    public GetAllItemsResponse(String responseCode, String responseStatus, String responseMessage, List<Item> itemList)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.data = itemList;
    }
}
