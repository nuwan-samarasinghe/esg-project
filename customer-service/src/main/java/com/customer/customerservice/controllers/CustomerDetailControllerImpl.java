package com.customer.customerservice.controllers;

import com.customer.customerservice.commons.Constants;
import com.customer.customerservice.commons.ValidUUID;
import com.customer.customerservice.commons.exceptions.CustomerServiceException;
import com.customer.customerservice.dto.CustomerDetailDto;
import com.customer.customerservice.models.Customer;
import com.customer.customerservice.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * REST API handler to handle customer details
 */
@RestController
public class CustomerDetailControllerImpl implements CustomerDetailController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public CustomerDetailControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ResponseEntity<?> getCustomDetails(String customerId) {
        Customer customer = customerService.getCustomerDetailsForId(customerId);
        if (Objects.isNull(customer)) {
            throw new CustomerServiceException(Constants.RETRIEVE_ERROR_MSG, Constants.RETRIEVE_ERROR_CODE);
        }
        return ResponseEntity.ok(modelMapper.map(customer, CustomerDetailDto.class));
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerDetailDto customerDetailDto) {
        Customer customer = this.modelMapper.map(customerDetailDto, Customer.class);
        if (Objects.nonNull(customerDetailDto.getId())) {
            customer.setId(UUID.fromString(customerDetailDto.getId()));
        }
        String uuid = customerService.saveCustomerDetails(customer);
        customerDetailDto.setId(uuid);
        return ResponseEntity.ok(customerDetailDto);
    }

}
