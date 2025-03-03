package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public data responseData;

    public EquipmentResponse(String responseCode,String responseStatus, String responseMessage, List<Equipment> equipment)
    {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
        this.responseData = new data(equipment);
    }

    class data {
        public List<Equipment> equipment;

        public data(List<Equipment> equipment) {
            this.equipment = equipment;
        }
    }
}
