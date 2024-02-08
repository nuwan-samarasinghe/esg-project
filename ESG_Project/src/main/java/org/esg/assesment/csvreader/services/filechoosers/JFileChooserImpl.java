package org.esg.assesment.csvreader.services.filechoosers;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Objects;

/**
 * Author: NUWAN
 * Date: 2024-02-05
 * Description:
 * services that find files and return
 */
public class JFileChooserImpl implements FileChooser {
    public static final String USER_DIR = "user.dir";
    public static final String CSV_FILES_NAME = "CSV Files";
    public static final String CSV_EXT = "csv";
    private static JFileChooserImpl INSTANCE;

    private JFileChooserImpl() {
        // empty constructor
    }

    public static JFileChooserImpl getInstance() {
        if (Objects.nonNull(INSTANCE)) {
            return INSTANCE;
        } else {
            synchronized (JFileChooserImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JFileChooserImpl();
                }
                return INSTANCE;
            }
        }
    }

    public File getFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty(USER_DIR)));
        fileChooser.setFileFilter(new FileNameExtensionFilter(CSV_FILES_NAME, CSV_EXT));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            System.err.println("No file selected");
            return null;
        }
    }

}
