package kr.co.ymtech.bm.controller.dto;

import java.util.Date;

/**
 * 게시물 정보를 저장하는 클래스
 */
public class BoardGetDTO {

	// API 작성하는법
	/** index : 게시글 번호 */
	private Integer index;
	/** title : 게시글 제목 */
	private String title;
	/** text : 게시글 내용 */
	private String text;
	/** userId : 게시글 작성자 아이디 */
	private String userId;
	/** category : 게시글 카테고리 */
	private Integer category;
	/** createDate : 게시글 작성일 */
	private Date createDate;

	/**
	 * Method : 게시물 index 반환하는 메소드
	 * 
	 * @return : 게시물 index 반환
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Method : 게시물 index 설정하는 메소드
	 * 
	 * @param : index는 설정할 index 값을 가지고 있다.
	 * 
	 * @return : 게시물 index 값 설정
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Method : 게시물 title 반환하는 메소드
	 * 
	 * @return : 게시물 title 반환
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method : 게시물 title 설정하는 메소드
	 * 
	 * @param : title는 설정할 title 값을 가지고 있다.
	 * 
	 * @return : 게시물 title 값 설정
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method : 게시물 text 반환하는 메소드
	 * 
	 * @return : 게시물 text 반환
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method : 게시물 text 설정하는 메소드
	 * 
	 * @param : text는 설정할 text 값을 가지고 있다.
	 * 
	 * @return : 게시물 text 값 설정
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method : 게시물 userId 반환하는 메소드
	 * 
	 * @return : 게시물 userId 반환
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Method : 게시물 userId 설정하는 메소드
	 * 
	 * @param : userId는 설정할 userId 값을 가지고 있다.
	 * 
	 * @return : 게시물 userId 값 설정
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Method : 게시물 category 반환하는 메소드
	 * 
	 * @return : 게시물 category 반환
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * Method : 게시물 category 설정하는 메소드
	 * 
	 * @param : category는 설정할 category 값을 가지고 있다.
	 * 
	 * @return : 게시물 category 값 설정
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * Method : 게시물 createDate 반환하는 메소드
	 * 
	 * @return : 게시물 createDate 반환
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Method : 게시물 createDate 설정하는 메소드
	 * 
	 * @param : createDate는 설정할 createDate 값을 가지고 있다.
	 * 
	 * @return : 게시물 createDate 값 설정
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
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
