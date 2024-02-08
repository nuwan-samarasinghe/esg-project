package org.esg.assesment.csvreader.services.parsers;

import org.esg.assesment.csvreader.exceptions.CSVReaderRuntimeException;
import org.esg.assesment.csvreader.models.CustomerDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Author: NUWAN
 * Date: 2024-02-05
 * Description:
 * test class to test json conversion for the given csv
 */
public class CSVReaderTest {

    static CSVReader csvReader;

    @BeforeAll
    public static void init() {
        csvReader = new CSVReader(new CSVParserStrategy());
    }

    @Test
    void whenValidCsvFileGiven_ThenProcessData_ReturnCustomerDetails() {
        assertNotNull(csvReader.readCSV(new File("src/test/resources/testdata_rs1.csv")));
    }

    @Test
    void whenTitleOnlyCsvFileGiven_ThenProcessData_ReturnEmptyCustomerDetails() {
        CustomerDetails result = csvReader.readCSV(new File("src/test/resources/testdata_empty.csv"));
        assertNotNull(result);
        assertEquals(0, result.getCustomerDetails().size());
    }

    @Test
    void whenEmptyCsvFileGiven_ThenProcessData_ReturnEmptyCustomerDetails() {
        CustomerDetails result = csvReader.readCSV(new File("src/test/resources/empty.csv"));
        assertNotNull(result);
        assertEquals(0, result.getCustomerDetails().size());
    }

    @Test
    void whenNotExistingCsvFileGiven_ThenProcessData_ReturnException() {
        CSVReaderRuntimeException exception = assertThrows(CSVReaderRuntimeException.class, () -> csvReader.readCSV(new File("src/test/resources/fileNotAvailable.txt")));
        assertEquals("An error occurred while reading the file: src\\test\\resources\\fileNotAvailable.txt", exception.getMessage());
    }

    @Test
    void whenNoFileGiven_ThenProcessData_ReturnException() {
        CSVReaderRuntimeException exception = assertThrows(CSVReaderRuntimeException.class, () -> csvReader.readCSV(null));
        assertEquals("No Files selected", exception.getMessage());
    }

}
