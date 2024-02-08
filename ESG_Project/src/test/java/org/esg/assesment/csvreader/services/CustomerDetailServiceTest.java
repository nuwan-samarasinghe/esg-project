package org.esg.assesment.csvreader.services;

import org.esg.assesment.csvreader.exceptions.CSVReaderRuntimeException;
import org.esg.assesment.csvreader.services.parsers.CSVParserStrategy;
import org.esg.assesment.csvreader.services.parsers.CSVReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * testing CustomerDetailService
 */
public class CustomerDetailServiceTest {

    private static CustomerDetailService customerDetailService;
    private static HttpClient httpClient;

    @BeforeAll
    public static void init() {
        CSVReader csvReader = new CSVReader(new CSVParserStrategy());
        httpClient = mock(HttpClient.class);
        customerDetailService = new CustomerDetailServiceImpl(csvReader, httpClient);
    }

    @Test
    void whenValidCsvFileGiven_ThenProcessData_ReturnCreateCustomerSuccessfully() throws IOException, InterruptedException {
        File file = new File("src/test/resources/testdata_rs1.csv");
        HttpResponse response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        customerDetailService.process(file);
    }

    @Test
    void whenValidCsvFileGiven_ThenProcessData_ReturnCreateCustomerFailed() throws IOException, InterruptedException {
        File file = new File("src/test/resources/testdata_rs1.csv");
        HttpResponse response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(400);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(response);
        CSVReaderRuntimeException exception = assertThrows(CSVReaderRuntimeException.class, () -> customerDetailService.process(file));
        assertEquals("An error occurred while calling the customer service endpoint", exception.getMessage());
    }

    @Test
    void whenValidCsvFileGiven_ThenProcessData_ReturnErrorForConnection() throws IOException, InterruptedException {
        File file = new File("src/test/resources/testdata_rs1.csv");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException("Server not available"));
        CSVReaderRuntimeException exception = assertThrows(CSVReaderRuntimeException.class, () -> customerDetailService.process(file));
        assertEquals("An error occurred while calling the customer service endpoint", exception.getMessage());
    }

}
