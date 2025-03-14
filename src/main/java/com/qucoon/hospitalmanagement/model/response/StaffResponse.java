package com.qucoon.hospitalmanagement.model.response;

import com.qucoon.hospitalmanagement.model.entity.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public data responseData;

    public StaffResponse(String responseCode,String responseStatus, String responseMessage, List<Staff> staff)
    {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseStatus = responseStatus;
        this.responseData = new data(staff);
    }

    class data {
        public List<Staff> staff;

        public data(List<Staff> staff) {
            this.staff = staff;
        }
    }
}
