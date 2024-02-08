package org.esg.assesment.csvreader.services.filechoosers;

import java.io.File;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * interface to wrap the choosing file selection
 */
public interface FileChooser {

    /**
     * get a file using file choosers
     *
     * @return file object
     */
    File getFile();
}
