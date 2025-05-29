package com.outdeved.alure.error;

public class ServiceException extends RuntimeException {

    private String errorMessage;

    public ServiceException(ServiceExceptionType type) {
        super();
        this.errorMessage = type.errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

}
