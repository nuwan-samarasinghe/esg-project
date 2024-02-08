package com.customer.customerservice;

import com.customer.customerservice.commons.Constants;
import com.customer.customerservice.dto.CustomerDetailDto;
import com.customer.customerservice.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * test suite testing
 */
public class CustomerServiceControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    /*Get customer end point testing*/
    @Test
    void testWhenCallInvalidEndpoint_ThenCallTheRestEndpoint_ReturnError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{customerId}", "80f21d8c-2ca7-4f1a-96ca-d188d4c826FF")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testWhenSendAvailableCustomerId_ThenFindCustomerId_ReturnCustomerDetails() throws Exception {
        Optional<Customer> optionalCustomer = this.customerRepository.findAll().stream().findAny();
        Assertions.assertTrue(optionalCustomer.isPresent(), "No data present in the database");
        Customer customer = optionalCustomer.get();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{customerId}", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId().toString()));
    }

    @Test
    void testWhenSendNotAvailableCustomerId_ThenFindCustomerDetails_ReturnCustomerNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{customerId}", "80f21d8c-2ca7-4f1a-96ca-d188d4c826FF")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.RETRIEVE_ERROR_MSG))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(Constants.RETRIEVE_ERROR_CODE));
    }

    @Test
    void testWhenSendInvalidUUID_ThenFindCustomerDetails_ReturnValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{customerId}", "80")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testWhenSendEmptyId_ThenFindCustomerDetails_ReturnValidationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{customerId}", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /*POST customer end point testing*/
    @Test
    void testWhenSendCustomerDetails_ThenCreateCustomer_ReturnCustomerCreated() throws Exception {
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();
        customerDetailDto.setCustomerName("Nuwan");
        customerDetailDto.setCountry("United Kingdom");
        customerDetailDto.setCounty("West Yorkshire");
        customerDetailDto.setTown("Leeds");
        customerDetailDto.setAddressLine1("Address1");
        customerDetailDto.setAddressLine2("Address2");
        customerDetailDto.setPostcode("LS23ES");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDetailDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(customerDetailDto.getCustomerName()));
    }

    @Test
    void testWhenSendNullCustomerDetail_ThenCallCreateCustomer_ReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(Mockito.any(CustomerDetailDto.class))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testWhenSendAlreadyExistingCustomer_ThenCallCreateCustomer_ReturnError() throws Exception {
        Optional<Customer> optionalCustomer = this.customerRepository.findAll().stream().findAny();
        Assertions.assertTrue(optionalCustomer.isPresent(), "No data present in the database");
        Customer customer = optionalCustomer.get();
        ModelMapper modelMapper = new ModelMapper();
        CustomerDetailDto customerDetailDto = modelMapper.map(customer, CustomerDetailDto.class);
        customerDetailDto.setId(customer.getId().toString());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDetailDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(Constants.SAVE_ERROR_MSG))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(Constants.SAVE_ERROR_CODE));
    }

    @Test
    void testWhenSendCustomerDetailsWithInvalidId_ThenCreateCustomer_ReturnError() throws Exception {
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();
        customerDetailDto.setId("fsdfsdfsd");
        customerDetailDto.setCustomerName("Nuwan");
        customerDetailDto.setCountry("United Kingdom");
        customerDetailDto.setCounty("West Yorkshire");
        customerDetailDto.setTown("Leeds");
        customerDetailDto.setAddressLine1("Address1");
        customerDetailDto.setAddressLine2("Address2");
        customerDetailDto.setPostcode("LS23ES");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDetailDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }

    // Helper method to convert an object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
