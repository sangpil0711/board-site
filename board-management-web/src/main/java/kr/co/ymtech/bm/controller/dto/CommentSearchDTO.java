package kr.co.ymtech.bm.controller.dto;

import java.util.Date;
import java.util.List;

/**
 * 댓글 정보를 저장하는 클래스
 * 
 * @author 박상현
 * @since 2023. 09. 20.
 */
public class CommentSearchDTO {

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
	/** childs : 대댓글 */
	private List<CommentSearchDTO> childs; // 대댓글

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<CommentSearchDTO> getChilds() {
		return childs;
	}

	public void setChilds(List<CommentSearchDTO> childs) {
		this.childs = childs;
	}

}
