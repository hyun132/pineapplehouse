package com.ssafy.happyhouse.exception;

import com.ssafy.happyhouse.controller.HouseMapController;
import com.ssafy.happyhouse.exception.model.ErrorResult;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import javax.security.sasl.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class HappyHouseExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
    /**
     * 잘못된 요청시 예외 처리
     * code : 404
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult  exBadReqExHandle(IllegalArgumentException e) {
    	logger.error("[exceptionHandle] ", e);
        return new ErrorResult(new Date(), BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 잘못된 요청시 예외 처리 (POST,PUT등 지원하지 않는 method)
     * code : 405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodNotAllowedException.class)
    public ErrorResult illegalExHandle(MethodNotAllowedException e) {
    	logger.error("[exceptionHandle] ", e);
        return new ErrorResult(new Date(), METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    /**
     * 접근 권한 없는 자원에 접근시 예외 처리
     * code : 403
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ErrorResult exForbiddenExHandle(AccessDeniedException e) {
    	logger.error("[exceptionHandle] ", e);
        return new ErrorResult(new Date(), FORBIDDEN.value(), e.getMessage());
    }

    /**
     * 토큰 인증 관련 예외 처리
     *  code : 401
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ErrorResult exAuthHandle(AuthenticationException e) {
    	logger.error("[exceptionHandle] ", e);
        return new ErrorResult(new Date(), HttpStatus.UNAUTHORIZED.value(), "계정 권한이 유효하지 않습니다.");
    }

//    /**
//     * sql 관련 예외 처리
//     * 이걸..알려줄 필요가 있나..?
//     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
//    public ErrorResult exSqlHandle(SQLException e) {
//        log.error("[exceptionHandle] ", e);
//        return new ErrorResult(new Date(), "SQL", "서버 내부 오류");
//    }

    /**
     * 서버 내부 예외 처리
     * code : 5xx
     * 그 외 모든 예외 처리 (추후 커스텀 Exception 객체 만들면 추가해줄것)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity exHandle(Exception e) {
    	logger.info("[exceptionHandle] {}",e.getMessage());
        return new ResponseEntity(new ErrorResult(new Date(), INTERNAL_SERVER_ERROR.value(), "서버 내부 오류"), INTERNAL_SERVER_ERROR);
    }
}
