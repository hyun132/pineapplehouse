package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.QnADto;

public interface QnAService {

	// qna list 조회
	public Map<String, Object> listQnA(BoardParameterDto boardParameterDto) throws Exception;
	// qna 등록
	public boolean writeQnA(QnADto qnaDto) throws Exception;
	// qna 삭제
	public boolean deleteQnA(String qnano) throws Exception;
	// qna 수정 & comment 등록 & comment 수정 & comment 삭제
	public boolean modifyQnA(QnADto qnaDto) throws Exception;
	// qna + comment 조회
	public QnADto getQnA(String qnano) throws Exception;

}
