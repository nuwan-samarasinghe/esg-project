package org.esg.assesment.csvreader;

import org.esg.assesment.csvreader.services.CustomerDetailService;
import org.esg.assesment.csvreader.services.CustomerDetailServiceImpl;
import org.esg.assesment.csvreader.services.filechoosers.FileChooserFactory;
import org.esg.assesment.csvreader.services.filechoosers.FileChooserFactoryImpl;
import org.esg.assesment.csvreader.services.parsers.CSVParserStrategy;
import org.esg.assesment.csvreader.services.parsers.CSVReader;

import java.io.File;
import java.net.http.HttpClient;

/**
 * Author: NUWAN
 * Date: 2024-01-31
 * Description:
 * convert the csv files into json format
 */
public class CSVToJsonConverter {
    private final CustomerDetailService customerDetailService;
    private final FileChooserFactory fileChooserFactory;

    public CSVToJsonConverter() {
        CSVReader csvReader = new CSVReader(new CSVParserStrategy());
        HttpClient httpClient = HttpClient.newHttpClient();
        this.fileChooserFactory = new FileChooserFactoryImpl();
        this.customerDetailService = new CustomerDetailServiceImpl(csvReader, httpClient);
    }

    public static void main(String[] args) {
        CSVToJsonConverter csvToJsonConverter = new CSVToJsonConverter();
        csvToJsonConverter.printAppDetails();
        File selectedFile = csvToJsonConverter.fileChooserFactory.getFileChooser(FileChooserFactory.JFILECHOOSER).getFile();
        csvToJsonConverter.customerDetailService.process(selectedFile);
    }

    private void printAppDetails() {
        System.out.println("********************************************************");
        System.out.println("Name: Nuwan Samarasinghe");
        System.out.println("********************************************************");
        System.out.println("App Details:");
        System.out.println("  - Name: Java Json Converter");
        System.out.println("  - Version: 1.0.0v");
        System.out.println("  - Description: Converting csv file into json");
        System.out.println("********************************************************");
    }

}
