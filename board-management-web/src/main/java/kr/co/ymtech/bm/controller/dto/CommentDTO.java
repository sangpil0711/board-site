package kr.co.ymtech.bm.controller.dto;

public class CommentDTO {

	private Integer index;
	private Integer boardIndex;
	private String text;
	private Integer parentIndex;
	private String userId;
	private Long createDate;
	
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
	
	public Long getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	
	
	
}
