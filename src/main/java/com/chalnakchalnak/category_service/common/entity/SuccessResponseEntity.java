package com.chalnakchalnak.category_service.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SuccessResponseEntity<T>(
        @JsonProperty("result") T result
) {
    public static <T> SuccessResponseEntity<T> success(T result) {
        return new SuccessResponseEntity<>(result);
    }

    public static SuccessResponseEntity<Void> success() {
        return new SuccessResponseEntity<>(null);
    }
}