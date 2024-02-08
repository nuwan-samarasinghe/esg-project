package com.customer.customerservice.services;

import com.customer.customerservice.models.Customer;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * interface for the customer service
 */
public interface CustomerService {
    /**
     * get the customer details for the given id
     *
     * @param customerId customer primary key id
     * @return customer details
     */
    Customer getCustomerDetailsForId(String customerId);

    /**
     * save the customer details
     *
     * @param customer customer object
     * @return UUID of the saved customer
     */
    String saveCustomerDetails(Customer customer);
}
