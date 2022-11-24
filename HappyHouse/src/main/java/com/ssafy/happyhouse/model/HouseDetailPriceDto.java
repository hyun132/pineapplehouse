package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
@ApiModel(value = "HouseInfoDto : 아파트정보", description = "아파트의 상세 정보를 나타낸다.")
public class HouseDetailPriceDto {

	private String baseYearMonthDay;
	private String dealUpperPriceLimit;
	private String dealAveragePrice;
	private String dealLowPriceLimit;
	private String dealAveragePriceChangeAmount;
	private String leaseUpperPriceLimit;
	private String leaseAveragePrice;
	private String leaseLowPriceLimit;
	private String leaseAveragePriceChangeAmount;
	private String rentLowPrice;
	public String getBaseYearMonthDay() {
		return baseYearMonthDay;
	}
	public void setBaseYearMonthDay(String baseYearMonthDay) {
		this.baseYearMonthDay = baseYearMonthDay;
	}
	public String getDealUpperPriceLimit() {
		return dealUpperPriceLimit;
	}
	public void setDealUpperPriceLimit(String dealUpperPriceLimit) {
		this.dealUpperPriceLimit = dealUpperPriceLimit;
	}
	public String getDealAveragePrice() {
		return dealAveragePrice;
	}
	public void setDealAveragePrice(String dealAveragePrice) {
		this.dealAveragePrice = dealAveragePrice;
	}
	public String getDealLowPriceLimit() {
		return dealLowPriceLimit;
	}
	public void setDealLowPriceLimit(String dealLowPriceLimit) {
		this.dealLowPriceLimit = dealLowPriceLimit;
	}
	public String getDealAveragePriceChangeAmount() {
		return dealAveragePriceChangeAmount;
	}
	public void setDealAveragePriceChangeAmount(String dealAveragePriceChangeAmount) {
		this.dealAveragePriceChangeAmount = dealAveragePriceChangeAmount;
	}
	public String getLeaseUpperPriceLimit() {
		return leaseUpperPriceLimit;
	}
	public void setLeaseUpperPriceLimit(String leaseUpperPriceLimit) {
		this.leaseUpperPriceLimit = leaseUpperPriceLimit;
	}
	public String getLeaseAveragePrice() {
		return leaseAveragePrice;
	}
	public void setLeaseAveragePrice(String leaseAveragePrice) {
		this.leaseAveragePrice = leaseAveragePrice;
	}
	public String getLeaseLowPriceLimit() {
		return leaseLowPriceLimit;
	}
	public void setLeaseLowPriceLimit(String leaseLowPriceLimit) {
		this.leaseLowPriceLimit = leaseLowPriceLimit;
	}
	public String getLeaseAveragePriceChangeAmount() {
		return leaseAveragePriceChangeAmount;
	}
	public void setLeaseAveragePriceChangeAmount(String leaseAveragePriceChangeAmount) {
		this.leaseAveragePriceChangeAmount = leaseAveragePriceChangeAmount;
	}
	public String getRentLowPrice() {
		return rentLowPrice;
	}
	public void setRentLowPrice(String rentLowPrice) {
		this.rentLowPrice = rentLowPrice;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getRentUpperPrice() {
		return rentUpperPrice;
	}
	public void setRentUpperPrice(String rentUpperPrice) {
		this.rentUpperPrice = rentUpperPrice;
	}
	public String getUpperPriceLimit() {
		return upperPriceLimit;
	}
	public void setUpperPriceLimit(String upperPriceLimit) {
		this.upperPriceLimit = upperPriceLimit;
	}
	public String getAveragePriceLimit() {
		return averagePriceLimit;
	}
	public void setAveragePriceLimit(String averagePriceLimit) {
		this.averagePriceLimit = averagePriceLimit;
	}
	public String getLowPriceLimit() {
		return lowPriceLimit;
	}
	public void setLowPriceLimit(String lowPriceLimit) {
		this.lowPriceLimit = lowPriceLimit;
	}
	public String getPriceChangeAmount() {
		return priceChangeAmount;
	}
	public void setPriceChangeAmount(String priceChangeAmount) {
		this.priceChangeAmount = priceChangeAmount;
	}
	public String getLeasePerDealRate() {
		return leasePerDealRate;
	}
	public void setLeasePerDealRate(String leasePerDealRate) {
		this.leasePerDealRate = leasePerDealRate;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	private String deposit;
	private String rentUpperPrice;
	private String upperPriceLimit;
	private String averagePriceLimit;
	private String lowPriceLimit;
	private String priceChangeAmount;
	private String leasePerDealRate;
	private String aptCode;
	public HouseDetailPriceDto(String baseYearMonthDay, String dealUpperPriceLimit, String dealAveragePrice,
			String dealLowPriceLimit, String dealAveragePriceChangeAmount, String leaseUpperPriceLimit,
			String leaseAveragePrice, String leaseLowPriceLimit, String leaseAveragePriceChangeAmount,
			String rentLowPrice, String deposit, String rentUpperPrice, String upperPriceLimit,
			String averagePriceLimit, String lowPriceLimit, String priceChangeAmount, String leasePerDealRate,
			String aptCode) {
		super();
		this.baseYearMonthDay = baseYearMonthDay;
		this.dealUpperPriceLimit = dealUpperPriceLimit;
		this.dealAveragePrice = dealAveragePrice;
		this.dealLowPriceLimit = dealLowPriceLimit;
		this.dealAveragePriceChangeAmount = dealAveragePriceChangeAmount;
		this.leaseUpperPriceLimit = leaseUpperPriceLimit;
		this.leaseAveragePrice = leaseAveragePrice;
		this.leaseLowPriceLimit = leaseLowPriceLimit;
		this.leaseAveragePriceChangeAmount = leaseAveragePriceChangeAmount;
		this.rentLowPrice = rentLowPrice;
		this.deposit = deposit;
		this.rentUpperPrice = rentUpperPrice;
		this.upperPriceLimit = upperPriceLimit;
		this.averagePriceLimit = averagePriceLimit;
		this.lowPriceLimit = lowPriceLimit;
		this.priceChangeAmount = priceChangeAmount;
		this.leasePerDealRate = leasePerDealRate;
		this.aptCode = aptCode;
	}

}

