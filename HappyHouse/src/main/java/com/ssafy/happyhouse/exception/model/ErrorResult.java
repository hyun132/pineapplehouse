package com.ssafy.happyhouse.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 에러 발생했을 때 클라이언트에 전달할 데이터 담을 객체
 */
@Data
@AllArgsConstructor
public class ErrorResult {
    private Date timestamp;
    private Integer code;
    private String message;
	public ErrorResult(Date timestamp, int code, String message) {
		super();
		this.timestamp = timestamp;
		this.code = code;
		this.message = message;
	}
    
    
}
