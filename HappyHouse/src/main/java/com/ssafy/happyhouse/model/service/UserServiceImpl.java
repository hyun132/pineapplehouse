package com.ssafy.happyhouse.model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.controller.UserController;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.FavoriteMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(UserMapper.class).deleteRefreshToken(map);
	}
	
	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserid() == null || userDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(userid);
	}

	@Override
	public boolean joinUser(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).joinUser(userDto) == 1;
	}

	@Override
	public Map<String, Object> listUser(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		List<UserDto> users = sqlSession.getMapper(UserMapper.class).listUser(boardParameterDto);
		int count = sqlSession.getMapper(UserMapper.class).getCnt(boardParameterDto);
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		List<String> todayUsers = sqlSession.getMapper(UserMapper.class).getTodayUser();
		int todayUserCnt = todayUsers.size();
		int[] timeCnt = new int[24];
		for(String time : todayUsers) {
			int hours = Integer.parseInt(time.split(" ")[1].split(":")[0]);
			timeCnt[hours]++;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("users", users);
		map.put("count", count);
		map.put("todayUserCnt", todayUserCnt);
		map.put("timeCnt", timeCnt);
		return map;
	}

	@Override
	public UserDto getUser(String userId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}

	@Override
	public boolean updateUser(UserDto userDto) throws Exception {
		// boardMapper.deleteArticle(articleNo);
		return sqlSession.getMapper(UserMapper.class).updateUser(userDto) == 1;
	}

	@Override
	public boolean deleteUser(String userId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).deleteUser(userId) == 1;
	}

	@Override
	public boolean findPassword(UserDto userDto) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserMapper.class).findPassword(userDto) == 1;
	}

	@Override
	public boolean resetPassword(UserDto userDto) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserMapper.class).resetPassword(userDto) == 1;
	}

	@Override
	public boolean idCheck(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(UserMapper.class).idCheck(userid) == 1;
	}

	@Override
	public List<FavoriteDto> listFavorite(String userid) throws Exception {
		// TODO Auto-generated method stub
		logger.info(":" + userid + " " + sqlSession.getMapper(FavoriteMapper.class).listFavorite(userid));
		return sqlSession.getMapper(FavoriteMapper.class).listFavorite(userid);
	}
	

	@Override
	public int insertFavorite(FavoriteDto favoriteDto) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.getMapper(FavoriteMapper.class).insertFavorite(favoriteDto);
		logger.debug("너들어왔니??"+favoriteDto.getId());
		return favoriteDto.getId();
	}

	@Override
	public boolean deleteFavorite(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(FavoriteMapper.class).deleteFavorite(id) == 1;
	}



}
