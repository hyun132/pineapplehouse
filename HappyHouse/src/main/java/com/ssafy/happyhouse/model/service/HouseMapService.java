package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseChartDataDto;
import com.ssafy.happyhouse.model.HouseDetailDto;
import com.ssafy.happyhouse.model.HouseDetailPriceDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception;
	HouseDetailPriceDto getAptDetail(String aptNo)  throws Exception;
	List<HouseDetailDto> getAptsInDong(String dong)  throws Exception;
	HouseDetailDto getApt(String dong, String aptCode)  throws Exception;

	List<HouseChartDataDto> getAptPriceChartData(String aptCode) throws Exception;
}
