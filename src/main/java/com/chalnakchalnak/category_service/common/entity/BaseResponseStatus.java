package com.chalnakchalnak.category_service.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    // 400 Bad Request - 잘못된 파라미터
    BAD_REQUEST_INVALID_PARAM(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. 파라미터를 확인해주세요."),

    // 404 Not Found - 잘못된 경로 요청
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),

    // 405 Method Not Allowed - 허용되지 않은 HTTP 메서드
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP 메서드입니다."),

    // 500 Internal Server Error - 서버 내부 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다. 관리자에게 문의해주세요."),

    /**
     * 400 : security 에러
     */
    WRONG_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "다시 로그인 해주세요"),
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, "로그인을 먼저 진행해주세요"),
    NO_ACCESS_AUTHORITY(HttpStatus.FORBIDDEN, "접근 권한이 없습니다"),
    DISABLED_USER(HttpStatus.FORBIDDEN, "비활성화된 계정입니다. 계정을 복구하시겠습니까?"),
    FAILED_TO_RESTORE(HttpStatus.INTERNAL_SERVER_ERROR, "계정 복구에 실패했습니다. 관리자에게 문의해주세요."),

    /**
     * 900: 기타 에러
     */
    //INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 900, "Internal server error"),
    SSE_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "알림 전송에 실패하였습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "유효하지 입력입니다"),
    FAILED_TO_SAVE(HttpStatus.INTERNAL_SERVER_ERROR, "저장에 실패했습니다."),

    /**
     * 2000: users service error
     */
    // token
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."),

    // Users
    FAILED_TO_LOGIN(HttpStatus.UNAUTHORIZED, "아이디 또는 패스워드를 다시 확인하세요."),

    /**
     * 3000: product service error
     */

    // Category
    NO_EXIST_CATEGORY(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다"),
    DUPLICATED_CATEGORY(HttpStatus.CONFLICT, "이미 등록된 카테고리입니다"),

    // Report
    NO_EXIST_REPORT(HttpStatus.NOT_FOUND, "존재하지 않은 신고입니다."),


    /**
     * 6000: gpt-api error
     */
    // S3
    S3_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "파일 업로드에 실패하였습니다."),
    ;

    private final HttpStatusCode httpStatusCode;
    private final String message;
}
