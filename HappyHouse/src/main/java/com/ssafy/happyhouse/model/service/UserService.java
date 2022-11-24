package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
	boolean findPassword(UserDto userDto) throws Exception;
	boolean joinUser(UserDto userDto) throws Exception;
	boolean resetPassword(UserDto userDto) throws Exception;
	boolean idCheck(String userid) throws Exception;
	
	public List<FavoriteDto> listFavorite(String userid) throws Exception;
	public int insertFavorite(FavoriteDto favoriteDto) throws Exception;
	public boolean deleteFavorite(String id) throws Exception; 

	/* Admin */
	public Map<String, Object> listUser(BoardParameterDto boardParameterDto) throws Exception;
	UserDto getUser(String userId) throws Exception;
	boolean updateUser(UserDto userDto) throws Exception;
	boolean deleteUser(String userid) throws Exception;

}
