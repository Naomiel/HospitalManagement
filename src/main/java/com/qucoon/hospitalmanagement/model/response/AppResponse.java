package com.qucoon.hospitalmanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T> {
    private String responseCode;
    private String responseStatus;
    private String responseMessage;
    private T data;
}
