package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.controller.HouseMapController;
import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.mapper.CommentMapper;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class CommentServiceImpl implements CommentService {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CommentDto> listComment(String articleno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).list(articleno);
	}

	@Override
	public boolean create(CommentDto commentDto) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).create(commentDto) == 1;
	}

	@Override
	public boolean modify(CommentDto commentDto) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).modify(commentDto) == 1;
	}

	@Override
	public boolean delete(String commentno) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).delete(commentno) == 1;
	}

	@Override
	public List<CommentDto> getComment(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).getComment(userid);
	}

}
