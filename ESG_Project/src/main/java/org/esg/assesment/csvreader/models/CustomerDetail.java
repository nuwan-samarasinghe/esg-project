package org.esg.assesment.csvreader.models;

import java.util.Objects;

/**
 * Author: NUWAN
 * Date: 2024-01-31
 * Description:
 * Customer Details content
 */
public class CustomerDetail {
    private Integer customerRef;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;

    public Integer getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(Integer customerRef) {
        this.customerRef = customerRef;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "CustomerDetail{" +
                "customerRef=" + customerRef +
                ", customerName='" + customerName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", town='" + town + '\'' +
                ", county='" + county + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDetail)) return false;
        CustomerDetail that = (CustomerDetail) o;
        return Objects.equals(getCustomerRef(), that.getCustomerRef()) && Objects.equals(getCustomerName(), that.getCustomerName()) && Objects.equals(getAddressLine1(), that.getAddressLine1()) && Objects.equals(getAddressLine2(), that.getAddressLine2()) && Objects.equals(getTown(), that.getTown()) && Objects.equals(getCounty(), that.getCounty()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getPostcode(), that.getPostcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getCustomerRef(),
                getCustomerName(),
                getAddressLine1(),
                getAddressLine2(),
                getTown(),
                getCounty(),
                getCountry(),
                getPostcode());
    }
}
