package com.customer.customerservice.commons.exceptions;

import lombok.Getter;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * exception that is going to throw in the application
 */
public class CustomerServiceException extends RuntimeException {

    @Getter
    private Integer errorCode;

    public CustomerServiceException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomerServiceException(String message, Throwable throwable, Integer errorCode) {
        super(message, throwable);
        this.errorCode = errorCode;
    }
}
