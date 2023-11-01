package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardUpdateDTO {

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
	/** files : 게시글 첨부파일 */
	private List<MultipartFile> addFiles;
	private List<String> deleteFiles;

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * @return the createDate
	 */
	public Long getCreateDate() {
		return createDate;
	}

	/**
	 * @return the addFiles
	 */
	public List<MultipartFile> getAddFiles() {
		return addFiles;
	}

	/**
	 * @return the deletedFiles
	 */
	public List<String> getDeleteFiles() {
		return deleteFiles;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param addFiles the addFiles to set
	 */
	public void setAddFiles(List<MultipartFile> addFiles) {
		this.addFiles = addFiles;
	}

	/**
	 * @param deletedFiles the deletedFiles to set
	 */
	public void setDeleteFiles(List<String> deleteFiles) {
		this.deleteFiles = deleteFiles;
	}

	/**
	 * @Method toString
	 *
	 * @see java.lang.Object#toString()
	 *
	 * @return
	 *
	 * @author 황상필
	 * @since 2023. 11. 1.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardUpdateDTO [index=");
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
		builder.append(", addFiles=");
		builder.append(addFiles);
		builder.append(", deleteFiles=");
		builder.append(deleteFiles);
		builder.append("]");
		return builder.toString();
	}

}
