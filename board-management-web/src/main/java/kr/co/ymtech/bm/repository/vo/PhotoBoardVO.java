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

	/**
	 * Method : 기본 생성자
	 * 
	 * DTO는 주로 데이터 전송과 관련있으므로 getter setter 메서드를 가지고 있어 생성자를 사용안해도 됨
	 * VO는 값 그 자체 므로 객체로 설계되어 객체 생성 시점에 값이 설정되고 이후에는 변경되지 않아야 하므로 생성자를 사용해야한다.
	 * 기본생성자 사용 이유 :  기본 생성자를 정의하지 않으면 컴파일러가 자동으로 생성,
	 *                   하지만 매개변수가 있는 생성자를 정의할경우 기본 생성자는 자동으로 생성되지 않으므로 사용해야한다. 	  
	 *                   
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public PhotoBoardVO() {
		super();
	}

	/**
	 * Method : 모든 필드를 초기화하는 생성자
	 * 
	 * @param index      : 게시글의 인덱스
	 * @param title      : 게시글 제목
	 * @param text       : 게시글 내용
	 * @param userId     : 게시글 작성자의 사용자 ID
	 * @param category   : 게시글 카테고리
	 * @param createDate : 게시글 생성 날짜
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public PhotoBoardVO(Integer index, String title, String text, String userId, Integer category, Long createDate) {
		this.index = index;
		this.title = title;
		this.text = text;
		this.userId = userId;
		this.category = category;
		this.createDate = createDate;
	}

	/**
	 * Method : 게시물 index 반환하는 메소드
	 * 
	 * @return : 게시물 index 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Method : 게시물 index 설정하는 메소드
	 * 
	 * @param index: 설정할 index 값
	 * 
	 * @return : 게시물 index 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Method : 게시물 title 반환하는 메소드
	 * 
	 * @return : 게시물 title 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method : 게시물 title 설정하는 메소드
	 * 
	 * @param title: 설정할 title 값
	 * 
	 * @return : 게시물 title 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method : 게시물 text 반환하는 메소드
	 * 
	 * @return : 게시물 text 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method : 게시물 text 설정하는 메소드
	 * 
	 * @param text: 설정할 text 값
	 * 
	 * @return : 게시물 text 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method : 게시물 userId 반환하는 메소드
	 * 
	 * @return : 게시물 userId 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Method : 게시물 userId 설정하는 메소드
	 * 
	 * @param userId: 설정할 userId 값
	 * 
	 * @return : 게시물 userId 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Method : 게시물 category 반환하는 메소드
	 * 
	 * @return : 게시물 category 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * Method : 게시물 category 설정하는 메소드
	 * 
	 * @param category: 설정할 category 값
	 * 
	 * @return : 게시물 category 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * Method : 게시물 createDate 반환하는 메소드
	 * 
	 * @return : 게시물 createDate 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public Long getCreateDate() {
		return createDate;
	}

	/**
	 * Method : 게시물 createDate 설정하는 메소드
	 * 
	 * @param createDate: 설정할 createDate 값
	 * 
	 * @return : 게시물 createDate 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	/**
	 * Method : 객체(게시글 항목)를 문자열로 변환하는 메서드
	 * 
	 * BoardDTO 클래스의 필드 값을 문자열로 표현하여 반환하기 위해 쓰임
	 * 
	 * @return : 문자열을 반환
	 * 
	 * @author 박상현
	 * @since  2023.10.24
	 */
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
		builder.append("]");
		return builder.toString();
	}

}
