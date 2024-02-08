package com.customer.customerservice.services;

import com.customer.customerservice.commons.Constants;
import com.customer.customerservice.commons.exceptions.CustomerServiceException;
import com.customer.customerservice.models.Customer;
import com.customer.customerservice.repositorys.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * implementation for the customer service
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerDetailsForId(String customerId) {
        Optional<Customer> customer = customerRepository.findById(UUID.fromString(customerId));
        if (customer.isPresent()) {
            log.info("Given customer id exists and retrieved the data [{}]", customerId);
            return customer.get();
        }
        return null;
    }

    @Override
    public String saveCustomerDetails(Customer customer) {
        try {
            if (Objects.isNull(customer.getId()) || StringUtils.isBlank(customer.getId().toString()) || !customerRepository.existsById(customer.getId())) {
                UUID id = customerRepository.save(customer).getId();
                log.info("Successfully saved and the customer id is : [{}]", id);
                return id.toString();
            } else {
                throw new CustomerServiceException(Constants.SAVE_ERROR_MSG, Constants.SAVE_ERROR_CODE);
            }
        } catch (Exception ex) {
            log.error(Constants.SAVE_ERROR_MSG);
            throw new CustomerServiceException(Constants.SAVE_ERROR_MSG, ex, Constants.SAVE_ERROR_CODE);
        }
    }
}
