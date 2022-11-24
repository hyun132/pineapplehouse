package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/news")
@Api("뉴스 컨트롤러 API V1")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NewsService newsSerivce;

	@ApiOperation(value = "뉴스 목록", notes = "naver 부동산 뉴스 리스트를 가져온다. 그리고 뉴스 리스트가 담긴 map을 반환한다.", response = Map.class)
	@GetMapping("/{cityno}")
	public ResponseEntity<Map<String, Object>> listNews(@PathVariable("cityno") @ApiParam(value = "지역 코드", required = true) String cityno)
			throws Exception {
		logger.info("listNews - 호출");
		return new ResponseEntity<Map<String, Object>>(newsSerivce.getNewsList(cityno), HttpStatus.OK);

	}

}