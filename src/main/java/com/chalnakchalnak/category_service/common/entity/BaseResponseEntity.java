package com.chalnakchalnak.category_service.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponseEntity<T>(@Nullable T result) {

    public BaseResponseEntity() {
        this(null);
    }

}
