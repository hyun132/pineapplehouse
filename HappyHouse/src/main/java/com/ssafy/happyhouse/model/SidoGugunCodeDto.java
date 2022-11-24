package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@ApiModel(value = "SidoGugunCodeDto : 시도, 구군정보", description = "시도, 구군의 이름을 나타낸다.")
public class SidoGugunCodeDto {
	@ApiModelProperty(value = "동코드")
	private String regionCode;
	@ApiModelProperty(value = "동이름")
	private String regionName;


	public SidoGugunCodeDto(String regionCode, String regionName) {
		super();
		this.regionCode = regionCode;
		this.regionName = regionName;
	}



	public String getRegionCode() {
		return regionCode;
	}



	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}



	public String getRegionName() {
		return regionName;
	}



	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SidoGugunCodeDto [regionCode=");
		builder.append(regionCode);
		builder.append(", regionName=");
		builder.append(regionName);
		builder.append("]");
		return builder.toString();
	}


}
