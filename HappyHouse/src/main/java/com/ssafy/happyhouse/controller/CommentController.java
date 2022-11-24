package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러  API V1")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "articleno에 해당하는 댓글 목록을 반환한다.", response = List.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<List<CommentDto>> listComment(
			@PathVariable("articleno") @ApiParam(value = "게시글 번호.", required = true) String articleno) {
		logger.debug("listComment - 호출");

		return new ResponseEntity<List<CommentDto>>(commentService.listComment(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "userid에 해당하는 댓글 목록을 반환한다.", response = CommentDto.class)
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<CommentDto>> getUserComment(
			@PathVariable("userid") @ApiParam(value = "회원 id.", required = true) String userid) {
		logger.debug("getUserComment - 호출");

		return new ResponseEntity<List<CommentDto>>(commentService.getComment(userid), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 댓글을 입려간다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> creatComment(@RequestBody CommentDto commentDto) {
		logger.debug("createComment - 호출");

		boolean isWrite = commentService.create(commentDto);
		if (isWrite) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);

	}

	@ApiOperation(value = "commentno에 해당하는 댓글을 수정한다.", response = String.class)
	@PutMapping("/{commentno}")
	public ResponseEntity<String> modifyComment(
			@PathVariable("commentno") @ApiParam(value = "댓글 번호", required = true) String commentno,
			@RequestBody CommentDto commentDto) {
		logger.debug("modifyComment - 호출");

		commentDto.setCommentno(commentno);
		boolean isModify = commentService.modify(commentDto);

		if (isModify) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);

	}
	
	@ApiOperation(value = "commentno에 해당하는 댓글을 삭제한다.", response = String.class)
	@DeleteMapping("/{commentno}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentno") @ApiParam(value = "댓글 번호", required = true) String commentno) {
		logger.debug("deleteComment - 호출");
		
		boolean isDelete = commentService.delete(commentno);
		
		if(isDelete) {
			return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
		}
		
		return new ResponseEntity<String> (FAIL, HttpStatus.BAD_REQUEST);
	}
	
	

}
