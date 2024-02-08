package org.esg.assesment.csvreader.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: NUWAN
 * Date: 2024-01-31
 * Description:
 * list of CustomerDetails
 */
public class CustomerDetails {
    private final List<CustomerDetail> customerDetails = new ArrayList<>();

    public List<CustomerDetail> getCustomerDetails() {
        return customerDetails;
    }

    public void addCustomerDetails(CustomerDetail customerDetail) {
        this.customerDetails.add(customerDetail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDetails)) return false;
        CustomerDetails that = (CustomerDetails) o;
        return Objects.equals(getCustomerDetails(), that.getCustomerDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerDetails());
    }

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "customerDetails=" + customerDetails +
                '}';
    }
}
