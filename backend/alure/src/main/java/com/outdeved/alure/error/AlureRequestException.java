package com.outdeved.alure.error;

public class AlureRequestException extends RuntimeException {

    private String errorMessage;

    public AlureRequestException(AlureRequestExceptionType type) {
        super();
        this.errorMessage = type.errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
