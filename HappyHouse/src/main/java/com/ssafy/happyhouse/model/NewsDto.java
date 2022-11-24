package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NewsDto : 뉴스 정보", description = "뉴스의 제목, 내용을 나타낸다.")
public class NewsDto {
	
	@ApiModelProperty(value="기사 id")
	private String newsid;
	@ApiModelProperty(value="제목")
	private String title;
	@ApiModelProperty(value="내용")
	private String content;
	@ApiModelProperty(value="사진 링크")
	private String imgUrl;
	@ApiModelProperty(value="기사 링크")
	private String url;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	@Override
	public String toString() {
		return "NewsDto [news_id=" + newsid + ", title=" + title + ", content=" + content + ", imgUrl=" + imgUrl
				+ ", url=" + url + "]";
	}


}
