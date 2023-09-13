package kr.co.ymtech.bm.controller.dto;

import java.util.Date;

/**
 * 게시물 정보를 저장하는 클래스
 */
public class BoardGetDTO {

	// API 작성하는법
	/** index : 게시글 번호 */
	private Integer index; // 게시물 항목 변수 선언
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

	public Integer getIndex() { // 게시물 index 변환
		return index;
	}

	public void setIndex(Integer index) { // 게시물 index 설정
		this.index = index;
	}

	public String getTitle() { // 게시물 title 변환
		return title;
	}

	public void setTitle(String title) { // 게시물 title 설정
		this.title = title;
	}

	public String getText() { // 게시물 text 변환
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

	public Date getCreateDate() { // 게시물 createDate 변환
		return createDate;
	}

	public void setCreateDate(Date createDate) { // 게시물 createDate 설정
		this.createDate = createDate;
	}

}
