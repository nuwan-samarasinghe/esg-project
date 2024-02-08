package com.customer.customerservice.controllers;

import com.customer.customerservice.commons.ValidUUID;
import com.customer.customerservice.dto.CustomerDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * REST API handler to handle customer details
 */

@RequestMapping("/api")
public interface CustomerDetailController {

    @Operation(summary = "Get Customer Details")
    @GetMapping("/customer/{customerId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    ResponseEntity<?> getCustomDetails(@ValidUUID @PathVariable String customerId);


    @Operation(summary = "Create new customer")
    @PostMapping("/customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Customer already exists")
    })
    ResponseEntity<?> createCustomer(@RequestBody CustomerDetailDto customerDetailDto);
}
