package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.HouseChartDataDto;
import com.ssafy.happyhouse.model.HouseDetailDto;
import com.ssafy.happyhouse.model.HouseDetailPriceDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<SidoGugunCodeDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseDetailDto> getAptInDong(String dong);
	HouseDetailDto getApt(String dong,String aptCode);
	List<HouseChartDataDto> getAptPriceChartData(String aptCode);
	HouseDetailPriceDto getAptDetail(String aptCode);

}
