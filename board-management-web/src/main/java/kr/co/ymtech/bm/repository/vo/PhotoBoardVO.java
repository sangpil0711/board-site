package kr.co.ymtech.bm.repository.vo;

public class PhotoBoardVO {

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
	/** likeCount : 게시글 추천 수 */
	private Integer likeCount;
	

	/**
	 * @Method 기본 생성자
	 * 
	 * DTO는 주로 데이터 전송과 관련있으므로 getter setter 메서드를 가지고 있어 생성자를 사용안해도 됨
	 * VO는 값 그 자체 므로 객체로 설계되어 객체 생성 시점에 값이 설정되고 이후에는 변경되지 않아야 하므로 생성자를 사용해야한다.
	 * 기본생성자 사용 이유 :  기본 생성자를 정의하지 않으면 컴파일러가 자동으로 생성,
	 *                   하지만 매개변수가 있는 생성자를 정의할경우 기본 생성자는 자동으로 생성되지 않으므로 사용해야한다. 	  
	 *                   
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	public PhotoBoardVO() {
		super();
	}

	/**
	 * @Method 모든 필드를 초기화하는 생성자
	 * 
	 * @param index 게시글의 인덱스
	 * @param title 게시글 제목
	 * @param text 게시글 내용
	 * @param userId 게시글 작성자의 사용자 ID
	 * @param category 게시글 카테고리
	 * @param createDate 게시글 생성 날짜
	 * 
	 * @author 박상현
	 * @since 2023. 10. 24.
	 */
	public PhotoBoardVO(Integer index, String title, String text, String userId, Integer category, Long createDate, Integer likeCount) {
		this.index = index;
		this.title = title;
		this.text = text;
		this.userId = userId;
		this.category = category;
		this.createDate = createDate;
		this.likeCount = likeCount;
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
	
	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoBoardVO [index=");
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
		builder.append(", likeCount=");
		builder.append(likeCount);
		builder.append("]");
		return builder.toString();
	}

}
