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
    public String responseCode;
    public String responseStatus;
    public String responseMessage;
    public T data;
}
