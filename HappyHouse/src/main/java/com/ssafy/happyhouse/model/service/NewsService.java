package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.NewsDto;

public interface NewsService {
	
	public Map<String, Object> getNewsList(String cityno) throws Exception;

}
