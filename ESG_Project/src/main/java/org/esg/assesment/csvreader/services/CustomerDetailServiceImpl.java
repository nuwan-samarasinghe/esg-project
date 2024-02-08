package org.esg.assesment.csvreader.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.esg.assesment.csvreader.exceptions.CSVReaderRuntimeException;
import org.esg.assesment.csvreader.models.CustomerDetail;
import org.esg.assesment.csvreader.services.filechoosers.FileChooserFactory;
import org.esg.assesment.csvreader.services.parsers.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * service that convert the given file and send it to the service
 */
public class CustomerDetailServiceImpl implements CustomerDetailService {
    private final CSVReader csvReader;
    private final HttpClient httpClient;
    private final static String CUSTOMER_API_URL = "http://localhost:8080/api/customer";

    public CustomerDetailServiceImpl(CSVReader csvReader, HttpClient httpClient) {
        this.csvReader = csvReader;
        this.httpClient = httpClient;
    }

    @Override
    public void process(File selectedFile) {
        List<CustomerDetail> customerDetails = this.csvReader.readCSV(selectedFile).getCustomerDetails();
        for (CustomerDetail customerDetail : customerDetails) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(CUSTOMER_API_URL))
                        .header("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                        .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(customerDetail)))
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    String responseBody = response.body();
                    System.out.println("Response: " + responseBody);
                } else {
                    System.err.println("Error Message: " + response.body());
                    System.err.println("Error Status Code: " + response.statusCode());
                    throw new CSVReaderRuntimeException("An error occurred while calling the customer service endpoint");
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error Message: " + e.getMessage());
                throw new CSVReaderRuntimeException("An error occurred while calling the customer service endpoint");
            }
        }
    }
}
