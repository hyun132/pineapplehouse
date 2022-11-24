package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.controller.HouseMapController;
import com.ssafy.happyhouse.model.HouseChartDataDto;
import com.ssafy.happyhouse.model.HouseDetailDto;
import com.ssafy.happyhouse.model.HouseDetailPriceDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HouseMapServiceImpl implements HouseMapService {
    private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<SidoGugunCodeDto> getSido() throws Exception {
        return sqlSession.getMapper(HouseMapMapper.class).getSido();
    }

    @Override
    public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
        return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
    }

    @Override
    public List<SidoGugunCodeDto> getDongInGugun(String gugun) throws Exception {
        return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
    }

    @Override
    public HouseDetailPriceDto getAptDetail(String aptCode) throws Exception {
        logger.debug("getAptDetail {}", aptCode);
        return sqlSession.getMapper(HouseMapMapper.class).getAptDetail(aptCode);
    }

    
    @Override
    public List<HouseDetailDto> getAptsInDong(String dong) throws Exception {
        List<HouseDetailDto> aptInDong = sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
        logger.debug("getAptInDong {}", aptInDong);
        return aptInDong;
    }
    
    @Override
    public HouseDetailDto getApt(String dong, String aptCode) throws Exception {
    	HouseDetailDto aptInDong = sqlSession.getMapper(HouseMapMapper.class).getApt(dong,aptCode);
        logger.debug("getAptInDong {}", aptInDong);
        return aptInDong;
    }

    @Override
    public List<HouseChartDataDto> getAptPriceChartData(String aptCode) throws Exception {
        List<HouseChartDataDto> list = sqlSession.getMapper(HouseMapMapper.class).getAptPriceChartData(aptCode);
        logger.debug("getAptPriceChartData {}", list);
        return list;
    }
}
