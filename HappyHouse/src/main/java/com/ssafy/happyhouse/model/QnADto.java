package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnAtDto : qna게시판 정보", description = "qna게시판 정보를 나타낸다.")
public class QnADto {

	@ApiModelProperty(value = "qna 번호")
	private String qnano;
	@ApiModelProperty(value = "작성자 id")
	private String userid;
	@ApiModelProperty(value = "qna 제목")
	private String subject;
	@ApiModelProperty(value = "qna 내용")
	private String content;
	@ApiModelProperty(value = "qna 답변")
	private String answer;
	@ApiModelProperty(value = "qna 등록 시간")
	private String qnaregtime;
	@ApiModelProperty(value = "answer 등록 시간")
	private String ansregtime;

	public String getQnano() {
		return qnano;
	}

	public void setQnano(String qnano) {
		this.qnano = qnano;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQnaregtime() {
		return qnaregtime;
	}

	public void setQnaregtime(String qnaregtime) {
		this.qnaregtime = qnaregtime;
	}

	public String getAnsregtime() {
		return ansregtime;
	}

	public void setAnsregtime(String ansregtime) {
		this.ansregtime = ansregtime;
	}

	@Override
	public String toString() {
		return "QnADto [qnano=" + qnano + ", userid=" + userid + ", subject=" + subject + ", content=" + content
				+ ", answer=" + answer + ", qnaregtime=" + qnaregtime + ", ansregtime=" + ansregtime + "]";
	}

}
