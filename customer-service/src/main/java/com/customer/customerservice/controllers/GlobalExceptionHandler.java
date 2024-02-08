package com.customer.customerservice.controllers;

import com.customer.customerservice.commons.Constants;
import com.customer.customerservice.commons.exceptions.CustomerServiceException;
import com.customer.customerservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * controller adviser to handle all the global errors
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * handle CustomerServiceException with this method
     *
     * @param ex exception reference
     * @return ErrorDto as a json output
     */
    @ExceptionHandler(CustomerServiceException.class)
    public ResponseEntity<ErrorDto> handleException(CustomerServiceException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        errorDto.setErrorCode(ex.getErrorCode());
        if (Constants.RETRIEVE_ERROR_CODE.equals(errorDto.getErrorCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
        } else if (Constants.SAVE_ERROR_CODE.equals(errorDto.getErrorCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleException(IllegalArgumentException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(ex.getMessage());
        if (errorDto.getMessage().contains("Invalid UUID string")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
