package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.CommentDto;

public interface CommentService {
	
	List<CommentDto> listComment(String articleno);
	boolean create(CommentDto commentDto);
	boolean modify(CommentDto commentDto);
	boolean delete(String commentno);
	List<CommentDto> getComment(String userid);

}
