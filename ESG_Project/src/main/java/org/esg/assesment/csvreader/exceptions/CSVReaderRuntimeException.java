package org.esg.assesment.csvreader.exceptions;

/**
 * Author: NUWAN
 * Date: 2024-02-06
 * Description:
 * runtime exception wrapper
 */
public class CSVReaderRuntimeException extends RuntimeException {

    public CSVReaderRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CSVReaderRuntimeException(String message) {
        super(message);
    }
}
