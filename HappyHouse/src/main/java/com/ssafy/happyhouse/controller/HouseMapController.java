package com.ssafy.happyhouse.controller;

import java.util.List;

import com.ssafy.happyhouse.model.HouseChartDataDto;
import com.ssafy.happyhouse.model.HouseDetailDto;
import com.ssafy.happyhouse.model.HouseDetailPriceDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/house")
@Api("Map 컨트롤러  API V1")
@Slf4j
public class HouseMapController {

    private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

    @Autowired
    private HouseMapService haHouseMapService;

    @ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
        logger.info("sido - 호출{}",haHouseMapService.getSido());
        return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
    }

    @ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
    @GetMapping("/gugun")
    public ResponseEntity<List<SidoGugunCodeDto>> gugun(
            @RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
        logger.info("gugun - 호출");
        return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
    }

    //
//    @ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
//    @GetMapping(value = "/aptlist/{lawd_cd}/{deal_ymd}", produces = "application/json;charset=utf-8")
//    public ResponseEntity<String> aptList(@PathVariable("lawd_cd") String lawdCd, @PathVariable("deal_ymd") String dealYmd) throws IOException {
//        logger.info("sido - 호출");
//        String serviceKey = "C5RAypoIXCeQfU1sLG2XVJLRPwXPuvGeHkESeR5HHSQrnb9GCYmXFyXww9WvAmNVqDIYrb78l%2F2Wod9GXI%2FVZw%3D%3D";
//        StringBuilder urlBuilder = new StringBuilder(
//                "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
//         */
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
//                + "=" + serviceKey);
//        urlBuilder
//                .append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//                + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
//        urlBuilder.append(
//                "&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8")); /* 지역코드 */
//        urlBuilder.append(
//                "&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8")); /* 계약월 */
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
//
//        // XML.toJSONObject(JSON...) => XML을 JSON으로 만들어주는...
//        // pom.xml에 org.json 추가하면 됨...!!!
//        JSONObject json = XML.toJSONObject(sb.toString());
//        String jsonStr = json.toString();
//
//        return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
//    }
    @ApiOperation(value = "동 정보", notes = "선택한 구군의 동을 반환한다.", response = List.class)
    @GetMapping("/dong")
    public ResponseEntity<?> dong(@RequestParam("gugun") String gugun) throws Exception {
        logger.info("dong - 호출{}",gugun);
        return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
    }

    @ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
    @GetMapping("/{dong}")
    public ResponseEntity<?> apts(@PathVariable("dong") String dong) throws Exception {
    	logger.info("apt {}", dong);
        return new ResponseEntity<List<HouseDetailDto>>(haHouseMapService.getAptsInDong(dong), HttpStatus.OK);
    }
    
    @ApiOperation(value = "아파트정보", notes = "관심목록을 통해 상세화면에 접근하는 경우 하나의 아파트 정보를 리턴한다.", response = List.class)
    @GetMapping("/{dong}/{aptCode}")
    public ResponseEntity<?> apt(@PathVariable("dong") String dong, @PathVariable("aptCode") String aptCode) throws Exception {
    	logger.info("apt {} aptCode {}", dong, aptCode);
        return new ResponseEntity<HouseDetailDto>(haHouseMapService.getApt(dong, aptCode), HttpStatus.OK);
    }

    // 상세정보 띄울 때 필요한 정보들 https://new.land.naver.com/api/complexes/106178/prices?complexNo=106178&tradeType=A1&year=5&priceChartChange=false&areaNo=2&type=chart
    @ApiOperation(value = "아파트 상세", notes = "아파트 번호를 기준으로 아파트 상세내역을 반환한다.", response = List.class)
    @GetMapping("/houseDetail/{aptNo}")
    public ResponseEntity<?> aptDetail(@PathVariable("aptNo") String aptNo) throws Exception {
        logger.info("aptNo {} ", aptNo);
        return new ResponseEntity<HouseDetailPriceDto>(haHouseMapService.getAptDetail(aptNo), HttpStatus.OK);
    }

    @ApiOperation(value = "아파트 가격 변동내역", notes = "아파트 번호를 기준으로 아파트 상세내역을 반환한다.", response = List.class)
    @GetMapping("/housePriceHistory/{aptNo}")
    public ResponseEntity<?> chartData(@PathVariable("aptNo") String aptNo) throws Exception {
        logger.info("housePriceHistory {} ", aptNo);
        return new ResponseEntity<List<HouseChartDataDto>>(haHouseMapService.getAptPriceChartData(aptNo), HttpStatus.OK);
    }
}
