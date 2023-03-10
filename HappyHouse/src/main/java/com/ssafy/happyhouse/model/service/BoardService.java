package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.util.PageNavigation;

public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public Map<String, Object> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public Map<String, Object> listNotice(BoardParameterDto boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	public BoardDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	public List<BoardDto> listUserArticle(String userid) throws Exception; 
}
