package kr.co.ymtech.bm.controller.dto;

public class BoardDTO {

	private Integer index;  //게시물 항목 변수 선언
	private String title;
	private String text;
	private String userId;
	private Integer category;
	private Long createDate;

	public Integer getIndex() {  //게시물 index 변환
		return index;
	}

	public void setIndex(Integer index) {  //게시물 index 설정
		this.index = index;
	}

	public String getTitle() { // 게시물 title 변환
		return title;
	}

	public void setTitle(String title) { // 게시물 title 설정
		this.title = title;
	}

	public String getText() {  //게시물 text 변환
		return text;
	}

	public void setText(String text) { // 게시물 text 설정
		this.text = text;
	}

	public String getUserId() { // 게시물 userId 변환
		return userId;
	}

	public void setUserId(String userId) { // 게시물 userId 설정
		this.userId = userId;
	}

	public Integer getCategory() { // 게시물 category 변환
		return category;
	}

	public void setCategory(Integer category) { // 게시물 category 설정
		this.category = category;
	}

	public Long getCreateDate() { // 게시물 createDate 변환
		return createDate;
	}

	public void setCreateDate(Long createDate) { // 게시물 createDate 설정
		this.createDate = createDate;
	}

	@Override
	public String toString() { // 게시물 항목을 문자열로 변환하여 반환
		StringBuilder builder = new StringBuilder();
		builder.append("BoardDTO [index=");
		builder.append(index);
		builder.append(", title=");
		builder.append(title);
		builder.append(", text=");
		builder.append(text);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", category=");
		builder.append(category);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
}
