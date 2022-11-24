package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;


	@ApiOperation(value = "회원 등록", notes = "새로운 회원을 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> joinUser(
			@RequestBody @ApiParam(value = "회원 정보.", required = true) UserDto userDto) throws Exception {
		logger.info("registUser - 호출");
		logger.info(""+ userDto);
		if (userService.joinUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원 정보 보기", notes = "회원 ID에 해당하는 회원 정보를 반환한다.", response = UserDto.class)
	@GetMapping(value = "/{userid}")
	public ResponseEntity<UserDto> getUser(
			@PathVariable("userid") @ApiParam(value = "얻어올 회원 ID.", required = true) String userid) throws Exception {
		logger.info("getUser - 호출 : " + userid);
		return new ResponseEntity<UserDto>(userService.getUser(userid), HttpStatus.OK);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "회원 ID에 해당하는 회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyUser(
			@RequestBody @ApiParam(value = "수정할 회원정보.", required = true) UserDto userDto) throws Exception {
		logger.info("modifyUser - 호출 {}", userDto);

		if (userService.updateUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);

	}

	@ApiOperation(value = "회원 정보 삭제", notes = "회원 ID에 해당하는 회원 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping(value = "/{userid}")
	public ResponseEntity<String> deleteUser(
			@PathVariable("userid") @ApiParam(value = "살제할 회원 ID.", required = true) String userid) throws Exception {
		logger.info("deleteUser - 호출");
		if (userService.deleteUser(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "비밀번호 찾기", notes = "회원 ID, NAME, EMAIL에 해당하는 회원정보가 있는지 확인한다. 그리고 DB조회 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping(value = "/find")
	public ResponseEntity<String> findPassword(
			@RequestBody @ApiParam(value = "입력한 회원 정보.", required = true) UserDto userDto) throws Exception {
		logger.info("findPassword 호출: " + userDto.getUserid() + " " + userDto.getEmail() + " " + userDto.getUsername());
		if (userService.findPassword(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "비밀번호 재설정", notes = "회원 ID와 일치하는 정보의 비밀번호를 update한다. 그리고 DB변경 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping(value = "/reset")
	public ResponseEntity<String> resetPassword(
			@RequestBody @ApiParam(value = "입력한 회원 정보.", required = true) UserDto userDto) throws Exception {
		logger.info("resetPassword 호출: " + userDto);
		if (userService.resetPassword(userDto)) {
			logger.debug("성공");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		logger.debug("실패");
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "ID 체크", notes = "회원 ID와 일치하는 정보를 get한다. 그리고 DB조회 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@GetMapping("user/{userid}")
	public ResponseEntity<String> idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		if(userService.idCheck(userId))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserid());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserid());// key, data
				userService.saveRefreshToken(userDto.getUserid(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, userDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getUserid());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "관심 지역 정보 조회", notes = "회원 ID에 해당하는 관심 지역 정보를 조회한다.", response = List.class)
	@GetMapping("/favorite/{userid}")
	public ResponseEntity<List<FavoriteDto>> listFavorite(@PathVariable("userid") String userid) throws Exception {
		logger.info("getFavorite - 호출");

		return new ResponseEntity<List<FavoriteDto>>(userService.listFavorite(userid), HttpStatus.OK);

	}
	
	@ApiOperation(value = "관심 지역 정보 추가", notes = "회원 ID에 해당하는 관심 지역 정보를 추가한다.", response = List.class)
	@PostMapping("/favorite")
	public ResponseEntity<?> addFavorite(@RequestBody FavoriteDto favoriteDto) throws Exception {
		logger.info("addFavorite - 호출");
		try{
			Integer result = userService.insertFavorite(favoriteDto);
			return new ResponseEntity<Integer>(result ,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);	
		}
	}
	
	@ApiOperation(value = "관심 지역 정보 삭제", notes = "회원 ID에 해당하는 관심 지역 정보를 조회한다.", response = List.class)
	@DeleteMapping("/favorite/{id}")
	public ResponseEntity<Void> deleteFavorite(@PathVariable("id") String id) throws Exception {
		logger.info("deleteFavorite - 호출");
		try {
			logger.info("delete id있니??"+ id);
			userService.deleteFavorite(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);	
		}
	}
}
