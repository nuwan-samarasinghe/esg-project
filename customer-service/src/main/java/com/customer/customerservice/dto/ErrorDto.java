package com.customer.customerservice.dto;

import lombok.Data;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * error message as a json
 */
@Data
public class ErrorDto {
    public String message;
    public Integer errorCode;
}
