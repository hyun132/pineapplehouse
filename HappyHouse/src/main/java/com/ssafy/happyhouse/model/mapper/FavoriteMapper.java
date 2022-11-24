package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.FavoriteDto;

public interface FavoriteMapper {

	List<FavoriteDto> listFavorite(String userid) throws SQLException;
	int insertFavorite(FavoriteDto favoriteDto) throws SQLException;
	int deleteFavorite(String id) throws SQLException;
	
}
