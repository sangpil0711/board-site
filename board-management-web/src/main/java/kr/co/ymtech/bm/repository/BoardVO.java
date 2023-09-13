package kr.co.ymtech.bm.repository;

public class BoardVO {

	private Integer index; // 게시물 항목 변수 선언
	private String title;
	private String text;
	private String userId;
	private Integer category;
	private Long createDate;

	public BoardVO() {
		super();
	}

	public BoardVO(Integer index, String title, String text, String userId, Integer category, Long createDate) {
		this.index = index;
		this.title = title;
		this.text = text;
		this.userId = userId;
		this.category = category;
		this.createDate = createDate;
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

}
