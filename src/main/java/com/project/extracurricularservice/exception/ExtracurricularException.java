package com.project.extracurricularservice.exception;

public class ExtracurricularException extends RuntimeException{
    public ExtracurricularException() {
        super();
    }

    public ExtracurricularException(String message) {
        super(message);
    }

    public ExtracurricularException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExtracurricularException(Throwable cause) {
        super(cause);
    }

    protected ExtracurricularException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
