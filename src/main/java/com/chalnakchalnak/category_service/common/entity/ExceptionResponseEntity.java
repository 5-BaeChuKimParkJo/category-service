package com.chalnakchalnak.category_service.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatusCode;

public record ExceptionResponseEntity<T>(@JsonIgnore HttpStatusCode httpStatus, String message) {

    public ExceptionResponseEntity(BaseResponseStatus status) {
        this(status.getHttpStatusCode(), status.getMessage());
    }

    public ExceptionResponseEntity(BaseResponseStatus status, String message) {
        this(status.getHttpStatusCode(), message);
    }
}
