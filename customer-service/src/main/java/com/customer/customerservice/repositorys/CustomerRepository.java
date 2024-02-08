package com.customer.customerservice.repositorys;

import com.customer.customerservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * get data from database
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
