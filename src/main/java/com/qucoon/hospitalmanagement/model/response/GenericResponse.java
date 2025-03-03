package com.qucoon.hospitalmanagement.model.response;

public class GenericResponse {
    public String responseCode;
    public String responseStatus;
    public String responseMessage;

    public GenericResponse(String responseCode, String responseStatus, String responseMessage)
    {
        this.responseCode = responseCode;
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }
}
