package com.chalnakchalnak.category_service.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    // 400 Bad Request - 잘못된 파라미터
    BAD_REQUEST_INVALID_PARAM(HttpStatus.BAD_REQUEST, 400, "잘못된 요청입니다. 파라미터를 확인해주세요."),

    // 404 Not Found - 잘못된 경로 요청
    NOT_FOUND(HttpStatus.NOT_FOUND, 404, "요청한 리소스를 찾을 수 없습니다."),

    // 405 Method Not Allowed - 허용되지 않은 HTTP 메서드
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 405, "허용되지 않은 HTTP 메서드입니다."),

    // 500 Internal Server Error - 서버 내부 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500,"서버 내부 오류가 발생했습니다. 관리자에게 문의해주세요."),

    INVALID_INPUT(HttpStatus.BAD_REQUEST, 902, "유효하지 입력입니다"),

    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, 402, "로그인을 먼저 진행해주세요"),

    // Category
    NO_EXIST_CATEGORY(HttpStatus.NOT_FOUND, 2000,"존재하지 않는 카테고리입니다"),
    DUPLICATED_CATEGORY(HttpStatus.CONFLICT, 2001, "이미 등록된 카테고리입니다"),
    ;

    private final HttpStatusCode httpStatusCode;
    private final int code;
    private final String message;
}
