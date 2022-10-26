package com.sigecap.sigecapexamenbackend.exception;

public class PdfException extends FileException {
    private static final String DESCRIPTION = "File exception";

    public PdfException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
