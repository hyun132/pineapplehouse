package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.CommentDto;

public interface CommentMapper {
	
	List<CommentDto> list(String articleno);
	int create(CommentDto commentDto);
	int modify(CommentDto commentDto);
	int delete(String commentDto);
	List<CommentDto> getComment(String userid);

}
