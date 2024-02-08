package org.esg.assesment.csvreader.services;

import java.io.File;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * interface for the service
 */
public interface CustomerDetailService {

    /**
     * select a file and generate json & call customer service
     *
     * @param selectedFile selected file from the file chooser
     */
    void process(File selectedFile);
}
