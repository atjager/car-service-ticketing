package com.aj.carserviceticketing.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends ApiErrorException {

    public ItemNotFoundException(String string, String valueOf) {
        super(string, valueOf);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
