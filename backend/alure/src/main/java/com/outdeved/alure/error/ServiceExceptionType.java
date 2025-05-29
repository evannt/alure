package com.outdeved.alure.error;

public enum ServiceExceptionType {

    SERVER_ERROR("A service error occured while attempting to fetch weather data: %s"),
    UNHANDLED_IO_EXCEPTION("Unhandled IOException: %s");

    public final String errorMessage;

    private ServiceExceptionType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
