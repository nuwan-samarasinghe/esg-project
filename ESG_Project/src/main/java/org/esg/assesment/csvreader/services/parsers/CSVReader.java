package org.esg.assesment.csvreader.services.parsers;

import org.esg.assesment.csvreader.models.CustomerDetails;

import java.io.File;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * context class that parses given file into a CustomerDetails
 */
public class CSVReader {

    private final ParserStrategy parserStrategy;

    public CSVReader(ParserStrategy parserStrategy) {
        this.parserStrategy = parserStrategy;
    }

    public CustomerDetails readCSV(File selectFile) {
        return parserStrategy.convert(selectFile);
    }
}
