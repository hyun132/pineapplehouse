package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.controller.UserController;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private SqlSession sqlSession;

	// qna list 조회
	@Override
	public Map<String, Object> listQnA(BoardParameterDto boardParameterDto) throws Exception {
		// TODO Auto-generated method stub
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		List<QnADto> qna = sqlSession.getMapper(QnAMapper.class).listQnA(boardParameterDto);
		int count = sqlSession.getMapper(QnAMapper.class).getCnt();
//		logger.info("" + count);
		Map<String, Object> map = new HashMap<>();
		map.put("qna", qna);
		map.put("count", count);
		return map;
	}

	// qna 등록
	@Override
	public boolean writeQnA(QnADto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		if (qnaDto.getSubject() == null || qnaDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnAMapper.class).writeQnA(qnaDto) == 1;
	}

	// qna 삭제
	@Override
	public boolean deleteQnA(String qnano) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnAMapper.class).deleteQnA(qnano) == 1;
	}

	// qna 수정 & comment 등록 & comment 수정 & comment 삭제
	@Override
	public boolean modifyQnA(QnADto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnAMapper.class).modifyQnA(qnaDto) == 1;
	}

	// qna + comment 조회
	@Override
	public QnADto getQnA(String qnano) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(QnAMapper.class).getQnA(qnano);
	}

}
