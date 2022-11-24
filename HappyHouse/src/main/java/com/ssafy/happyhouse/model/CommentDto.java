package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 댓글 정보", description = "댓글 정보를 나타낸다.")
public class CommentDto {

	@ApiModelProperty(value = "댓글 번호")
	private String commentno;
	@ApiModelProperty(value = "회원 id")
	private String userid;
	@ApiModelProperty(value = "댓글")
	private String comment;
	@ApiModelProperty(value = "댓글 작성일")
	private String commenttime;
	@ApiModelProperty(value = "게시글 번호")
	private String articleno;

	public String getCommentno() {
		return commentno;
	}

	public void setCommentno(String commentno) {
		this.commentno = commentno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}

	@Override
	public String toString() {
		return "CommentDto [commentno=" + commentno + ", userid=" + userid + ", comment=" + comment + ", commenttime="
				+ commenttime + ", articleno=" + articleno + "]";
	}
}
