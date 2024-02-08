package org.esg.assesment.csvreader.services.parsers;

import org.esg.assesment.csvreader.exceptions.CSVReaderRuntimeException;
import org.esg.assesment.csvreader.models.CustomerDetail;
import org.esg.assesment.csvreader.models.CustomerDetails;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * implementation of the conversion from csv to given an object
 */
public class CSVParserStrategy implements ParserStrategy {
    @Override
    public CustomerDetails convert(File selectedFile) {
        if (Objects.isNull(selectedFile)) {
            System.err.println("No Files selected");
            throw new CSVReaderRuntimeException("No Files selected");
        }
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        CustomerDetails details = new CustomerDetails();
        try (BufferedReader br = Files.newBufferedReader(selectedFile.toPath())) {
            System.out.println("Titles " + br.readLine());
            String dataLines;
            while ((dataLines = br.readLine()) != null) {
                if (!dataLines.isEmpty()) {
                    String[] splittedData = dataLines.split(",");
                    CustomerDetail customerDetail = new CustomerDetail();
                    customerDetail.setCustomerRef(Integer.valueOf(splittedData[0]));
                    customerDetail.setCustomerName(splittedData[1]);
                    customerDetail.setAddressLine1(splittedData[2]);
                    customerDetail.setAddressLine2(splittedData[3]);
                    customerDetail.setTown(splittedData[4]);
                    customerDetail.setCounty(splittedData[5]);
                    customerDetail.setCountry(splittedData[6]);
                    customerDetail.setPostcode(splittedData[7]);
                    details.addCustomerDetails(customerDetail);
                }
            }
        } catch (Exception ex) {
            System.err.println("An error occurred while reading the file: " + ex.getMessage());
            throw new CSVReaderRuntimeException("An error occurred while reading the file: " + ex.getMessage(), ex);
        }
        return details;
    }
}
