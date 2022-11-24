package com.ssafy.happyhouse.controller;

import java.util.Map;

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

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/qna")
@Api("QnA 컨트롤러  API V1")
public class QnAController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnaService;

	@ApiOperation(value = "qna 글목록", notes = "모든 qna 전체의 정보를 반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> listQnA(
			@ApiParam(value = "qna 전체을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto)
			throws Exception {
		logger.info("listQnA - 호출");
		logger.info("boardParameterDto:" + boardParameterDto.toString());

		return new ResponseEntity<Map<String, Object>>(qnaService.listQnA(boardParameterDto), HttpStatus.OK);
	}

	@ApiOperation(value = "qna 글작성", notes = "새로운 qna 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQnA(
			@RequestBody @ApiParam(value = "qna 전체 정보.", required = true) QnADto qnaDto) throws Exception {
		logger.info("writeQnA - 호출");
		if (qnaService.writeQnA(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "qna 글보기", notes = "qna번호에 해당하는 qna 정보를 반환한다.", response = QnADto.class)
	@GetMapping("/{qnano}")
	public ResponseEntity<QnADto> getQnA(
			@PathVariable("qnano") @ApiParam(value = "얻어올 글의 qna번호.", required = true) String qnano)
			throws Exception {
		logger.info("getQnA - 호출 : " + qnano);
		return new ResponseEntity<QnADto>(qnaService.getQnA(qnano), HttpStatus.OK);
	}

	@ApiOperation(value = "qna 글수정", notes = "수정할 qna 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyQnA(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QnADto qnaDto) throws Exception {
		logger.info("modifyQnA - 호출 {}", qnaDto);

		if (qnaService.modifyQnA(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "qna 글삭제", notes = "qna번호에 해당하는 qna의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qnano}")
	public ResponseEntity<String> deleteQnA(
			@PathVariable("qnano") @ApiParam(value = "살제할 글의 qna번호.", required = true) String qnano)
			throws Exception {
		logger.info("deleteQnA - 호출");
		if (qnaService.deleteQnA(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
