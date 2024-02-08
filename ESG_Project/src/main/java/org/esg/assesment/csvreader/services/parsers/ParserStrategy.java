package org.esg.assesment.csvreader.services.parsers;

import org.esg.assesment.csvreader.models.CustomerDetails;

import java.io.File;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * parser interface that gets file and convert into a list of CustomerDetails objects
 */
public interface ParserStrategy {

    /**
     * convert given file into CustomerDetails object
     *
     * @param selectFile selected file
     * @return CustomerDetails object
     */
    CustomerDetails convert(File selectFile);
}
