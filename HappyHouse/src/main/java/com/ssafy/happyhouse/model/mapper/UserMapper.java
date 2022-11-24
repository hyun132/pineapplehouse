package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {

	public UserDto login(UserDto userDto) throws SQLException;
	public UserDto userInfo(String userid) throws SQLException;
	int joinUser(UserDto userDto) throws SQLException;
	int findPassword(UserDto userDto) throws SQLException;
	int resetPassword(UserDto userDto) throws SQLException;
	int idCheck(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	public List<String> getTodayUser() throws SQLException;
	

	/* Admin */
	List<UserDto> listUser(BoardParameterDto boardParameterDto) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	int updateUser(UserDto userDto) throws SQLException;
	int deleteUser(String userId) throws SQLException;
	int getCnt(BoardParameterDto boardParameterDto);


}
