package com.ssafy.happyhouse.model;

import lombok.*;

import java.util.ArrayList;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Data
public class HouseChartDataDto {
    public String aptCode;
    public String marketPrice;
    public String upperMarketPrice;
    public String lowerMarketPrice;
    public String ChartBasisDate;
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getUpperMarketPrice() {
		return upperMarketPrice;
	}
	public void setUpperMarketPrice(String upperMarketPrice) {
		this.upperMarketPrice = upperMarketPrice;
	}
	public String getLowerMarketPrice() {
		return lowerMarketPrice;
	}
	public void setLowerMarketPrice(String lowerMarketPrice) {
		this.lowerMarketPrice = lowerMarketPrice;
	}
	public String getChartBasisDate() {
		return ChartBasisDate;
	}
	public void setChartBasisDate(String chartBasisDate) {
		ChartBasisDate = chartBasisDate;
	}
	public HouseChartDataDto(String aptCode, String marketPrice, String upperMarketPrice, String lowerMarketPrice,
			String chartBasisDate) {
		super();
		this.aptCode = aptCode;
		this.marketPrice = marketPrice;
		this.upperMarketPrice = upperMarketPrice;
		this.lowerMarketPrice = lowerMarketPrice;
		ChartBasisDate = chartBasisDate;
	}

}
