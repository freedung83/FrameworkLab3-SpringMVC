package com.multicampus.biz.board.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="BOARD")
@SequenceGenerator(name="boardSequence", 
                   sequenceName="BOARD_SEQ",
                   initialValue=1, 
                   allocationSize=1)
public class BoardVO {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, 
                    generator="boardSequence")
	private int seq;
	private String title;
	@Column(updatable=false)
	private String writer;
	private String content;
	private Date regDate = new Date(System.currentTimeMillis());
	private int cnt;		
	// 검색 관련 변수
	@Transient
	private String searchCondition;
	@Transient
	private String searchKeyword;
	@Transient
	private MultipartFile uploadFile;
	private String fileName;	
	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer="
				+ writer + ", content=" + content + ", regDate=" + regDate
				+ ", cnt=" + cnt + ", searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + "]";
	}
}
