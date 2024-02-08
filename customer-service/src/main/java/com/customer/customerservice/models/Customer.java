package com.customer.customerservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * customer entity
 */

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @UuidGenerator
    private UUID Id;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;
}
