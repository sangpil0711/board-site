package kr.co.ymtech.bm.controller.dto;

import java.util.Date;

/**
 * 댓글 정보를 저장하는 클래스
 * 
 * @author 박상현
 * @since  2023.09.20
 */
public class CommentGetDTO {

	/** index : 댓글 번호 */
	private Integer index;
	/** boardIndex : 게시물 번호 */
	private Integer boardIndex;
	/** text : 댓글 내용 */
	private String text;
	/** parentIndex : 상위 댓글 번호 */
	private Integer parentIndex;
	/** userId : 댓글 작성자 아이디 */
	private String userId;
	/** createDate : 댓글 작성일 */
	private Date createDate;

	/**
	 * Method : 댓글 index 반환하는 메소드
	 * 
	 * @return : 댓글 index 반환
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * Method : 댓글 index 설정하는 메소드
	 * 
	 * @param : index는 설정할 index 값을 가지고 있다.
	 * 
	 * @return : 댓글 index 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * Method : 댓글 BoardIndex 반환하는 메소드
	 * 
	 * @return : 댓글 BoardIndex 반환
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public Integer getBoardIndex() {
		return boardIndex;
	}

	/**
	 * Method : 댓글 boardIndex 설정하는 메소드
	 * 
	 * @param : boardIndex는 설정할 boardIndex 값을 가지고 있다.
	 * 
	 * @return : 댓글 boardIndex 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public void setBoardIndex(Integer boardIndex) {
		this.boardIndex = boardIndex;
	}

	/**
	 * Method : 댓글 Text 반환하는 메소드
	 * 
	 * @return : 댓글 Text 반환
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public String getText() {
		return text;
	}

	/**
	 * Method : 댓글 Text 설정하는 메소드
	 * 
	 * @param : text는 설정할 text 값을 가지고 있다.
	 * 
	 * @return : 댓글 Text 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method : 댓글 ParentIndex 반환하는 메소드
	 * 
	 * @return : 댓글 ParentIndex 반환
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public Integer getParentIndex() {
		return parentIndex;
	}

	/**
	 * Method : 댓글 parentIndex 설정하는 메소드
	 * 
	 * @param : parentIndex는 설정할 parentIndex 값을 가지고 있다.
	 * 
	 * @return : 댓글 parentIndex 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public void setParentIndex(Integer parentIndex) {
		this.parentIndex = parentIndex;
	}

	/**
	 * Method : 댓글 userId 반환하는 메소드
	 * 
	 * @return : 댓글 userId 반환
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Method : 댓글 userId 설정하는 메소드
	 * 
	 * @param : userId는 설정할 userId 값을 가지고 있다.
	 * 
	 * @return : 댓글 userId 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Method : 댓글 createDate 반환하는 메소드
	 * 
	 * @return : 댓글 createDate 반환
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Method : 댓글 createDate 설정하는 메소드
	 * 
	 * @param : createDate는 설정할 createDate 값을 가지고 있다.
	 * 
	 * @return : 댓글 createDate 값 설정
	 * 
	 * @author 박상현
	 * @since  2023.09.20
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
