package kr.co.ymtech.bm.controller.dto;

import java.util.Date;

public class CommentGetDTO {


	private Integer index;
	private Integer boardIndex;
	private String text;
	private Integer parentIndex;
	private String userId;
	private Date createDate;
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public Integer getBoardIndex() {
		return boardIndex;
	}
	
	public void setBoardIndex(Integer boardIndex) {
		this.boardIndex = boardIndex;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getParentIndex() {
		return parentIndex;
	}
	
	public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	
	
	
}
