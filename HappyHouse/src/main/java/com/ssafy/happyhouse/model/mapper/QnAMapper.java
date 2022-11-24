package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.QnADto;

@Mapper
public interface QnAMapper {

	public List<QnADto> listQnA(BoardParameterDto boardParameterDto) throws SQLException;

	public int writeQnA(QnADto qnaDto) throws SQLException;

	public int deleteQnA(String qnano) throws SQLException;

	public int modifyQnA(QnADto qnaDto) throws SQLException;

	public QnADto getQnA(String qnano) throws SQLException;
	
	public int getCnt() throws SQLException;

}
