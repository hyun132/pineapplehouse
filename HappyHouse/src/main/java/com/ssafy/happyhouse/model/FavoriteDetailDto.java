package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FavoriteDto : 관심 지역 정보", description = "관심 지역 정보를 나타낸다.")
public class FavoriteDetailDto {

	@ApiModelProperty(value = "관심 지역 정보 행 id")
	int id;
	@ApiModelProperty(value = "회원 id")
	String userid;
	@ApiModelProperty(value = "동 코드")
	String dongCode;
	@ApiModelProperty(value = "아파트 코드")
	String aptCode;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		return "FavoriteDto [id=" + id + ", userid=" + userid + ", dongCode=" + dongCode + ", aptCode=" + aptCode + "]";
	}

}
