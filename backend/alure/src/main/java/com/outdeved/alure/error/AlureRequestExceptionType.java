package com.outdeved.alure.error;

public enum AlureRequestExceptionType {

    NO_LOCATION_FOUND(
            "No weather information could be found for the provided location. Please specify a valid location.");

    public final String errorMessage;

    private AlureRequestExceptionType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
