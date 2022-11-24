package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Api("관리자 컨트롤러  API V1")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    private UserService userService;
    private BoardService boardService;

    private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

    @Autowired
    public AdminController(UserService userService, BoardService boardService) {
        this.userService = userService;
        this.boardService = boardService;
    }
    
    @ApiOperation(value = "공지사항 목록", notes = "공지사항 목록을 반환한다", response = Map.class)
    @GetMapping("/notice")
    public ResponseEntity<Map<String, Object>> listNotice(@ApiParam(value = "공지사항 목록을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
    	logger.info("listNotice" + " - 호출");
		logger.info("boardParameterDto:" + boardParameterDto.toString());
		return new ResponseEntity<Map<String, Object>>(boardService.listNotice(boardParameterDto), HttpStatus.OK);
    }

    @ApiOperation(value = "회원 목록", notes = "모든 회원 정보를 반환한다", response = Map.class)
    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> listUser(@ApiParam(value = "회원목록을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
    	logger.info("listUser" + " - 호출");
		logger.info("boardParameterDto:" + boardParameterDto.toString());
		return new ResponseEntity<Map<String, Object>>(userService.listUser(boardParameterDto), HttpStatus.OK);

    }

    @ApiOperation(value = "회원 등록", notes = "새로운 회원을 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping(value = "/user")
    public ResponseEntity<String> registUser(@RequestBody @ApiParam(value = "회원 정보.", required = true) UserDto userDto) throws Exception {
    	logger.info("registUser - 호출");
		if (userService.joinUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "회원 정보 보기", notes = "회원 ID에 해당하는 회원 정보를 반환한다.", response = UserDto.class)
    @GetMapping(value = "/user/{userid}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userid") @ApiParam(value = "얻어올 회원 ID.", required = true) String userid) throws Exception {
    	logger.info("getUser - 호출 : " + userid);
		return new ResponseEntity<UserDto>(userService.getUser(userid), HttpStatus.OK);
    }


    @ApiOperation(value = "회원 정보 수정", notes = "회원 ID에 해당하는 회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PutMapping(value = "/user")
    public ResponseEntity<String> modifyUser(@RequestBody @ApiParam(value = "수정할 회원정보.", required = true) UserDto userDto) throws Exception {
    	logger.info("modifyUser - 호출 {}", userDto);
		
		if (userService.updateUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        
        
        
    }

    @ApiOperation(value = "회원 정보 삭제", notes = "회원 ID에 해당하는 회원 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping(value = "/user/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable("userid") @ApiParam(value = "살제할 회원 ID.", required = true) String userid) throws Exception {
    	logger.info("deleteUser - 호출");
		if (userService.deleteUser(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

}
