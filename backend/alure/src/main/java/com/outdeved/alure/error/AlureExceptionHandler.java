package com.outdeved.alure.error;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.outdeved.alure.util.ErrorUtil;

@ControllerAdvice
public class AlureExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException e) {

        String rootCause = ErrorUtil.getRootException(e).getMessage().toLowerCase();
        if (rootCause.contains("broken pipe")) {
            // Halt operations on broken pipes since nothing can be written
            return null;
        }

        ServiceException ex = new ServiceException(ServiceExceptionType.UNHANDLED_IO_EXCEPTION);
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlureRequestException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(AlureRequestException e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> handleException(Exception e, HttpStatus status) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<ErrorResponse>(response, headers, status);
    }

}
