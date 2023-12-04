package com.aj.carserviceticketing.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.aj.carserviceticketing.domain.nondb.error.Error;


@RestControllerAdvice
public class ApiErrorExceptionHandler {

    @ExceptionHandler(ItemAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Error> handleApiErrorException409(ItemAlreadyExistsException exc) {
        return error(exc);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handle(MethodArgumentNotValidException exc) {
        var validationList = exc.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : validationList) {
            errorMessage.append(String.format("%s %s. ", error.getField(), error.getDefaultMessage()));
        }
        return new ResponseEntity<>(new Error("validationError", errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Error> handleApiErrorException404(ItemNotFoundException exc) {
        return error(exc);
    }

    private ResponseEntity<Error> error(ApiErrorException exc) {
        return new ResponseEntity<>(new Error(exc.getErrorName(), exc.getErrorMessage()), exc.getHttpStatus());
    }
}
