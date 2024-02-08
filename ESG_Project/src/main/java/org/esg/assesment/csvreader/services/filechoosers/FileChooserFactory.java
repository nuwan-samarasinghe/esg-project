package org.esg.assesment.csvreader.services.filechoosers;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * interface for the file chooser factory
 */
public interface FileChooserFactory {
    String JFILECHOOSER = "JFileChooser";

    /**
     * select file chooser to select a file
     *
     * @param name name of the file chooser
     * @return file chooser object
     */
    FileChooser getFileChooser(String name);
}
