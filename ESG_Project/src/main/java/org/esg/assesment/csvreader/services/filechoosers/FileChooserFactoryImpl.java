package org.esg.assesment.csvreader.services.filechoosers;

/**
 * Author: NUWAN
 * Date: 2024-02-07
 * Description:
 * implementation to choose file selection methods
 */
public class FileChooserFactoryImpl implements FileChooserFactory {
    @Override
    public FileChooser getFileChooser(String name) {
        return name.equalsIgnoreCase(JFILECHOOSER) ? JFileChooserImpl.getInstance() : null;
    }
}
