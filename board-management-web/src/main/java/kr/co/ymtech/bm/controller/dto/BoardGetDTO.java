package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 게시물 정보를 저장하는 클래스
 * 
 * @author 황상필
 * @since 2023. 10. 31.
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
	private Long createDate;
	/** file : 등록된 게시글 첨부파일 */
	private List<FileVO> file;
	
	public List<FileVO> getFile() {
		return file;
	}
	
	public void setFile(List<FileVO> file) {
		this.file = file;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardGetDTO [index=");
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
		builder.append(", file=");
		builder.append(file);
		builder.append("]");
		return builder.toString();
	}
	
}
