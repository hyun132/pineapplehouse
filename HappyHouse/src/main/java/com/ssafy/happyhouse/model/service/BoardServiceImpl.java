package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.controller.UserController;
import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public Map<String, Object> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		List<BoardDto> articles = sqlSession.getMapper(BoardMapper.class).listArticle(boardParameterDto);
		int count = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);
		Map<String, Object> map = new HashMap<>();
		map.put("articles", articles);
		map.put("count", count);
		return map;
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteComment(articleno);
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public Map<String, Object> listNotice(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		List<BoardDto> articles = sqlSession.getMapper(BoardMapper.class).listNotice(boardParameterDto);
		int count = sqlSession.getMapper(BoardMapper.class).getTotalNoticeCount(boardParameterDto);
		logger.info("" + articles);
		logger.info("" + count);
		Map<String, Object> map = new HashMap<>();
		map.put("articles", articles);
		map.put("count", count);
		return map;
	}

	@Override
	public List<BoardDto> listUserArticle(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(BoardMapper.class).listUserArticle(userid);
	}
}