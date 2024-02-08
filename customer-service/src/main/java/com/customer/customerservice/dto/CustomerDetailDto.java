package com.customer.customerservice.dto;

import com.customer.customerservice.commons.ValidUUID;
import lombok.Data;

/**
 * Author: NUWAN
 * Date: 2024-01-31
 * Description:
 * Customer Details content
 */

@Data
public class CustomerDetailDto {
    private @ValidUUID String id;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;
}
