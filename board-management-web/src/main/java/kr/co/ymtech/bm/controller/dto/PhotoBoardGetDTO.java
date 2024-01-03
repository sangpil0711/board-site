package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.FileVO;

/**
 * 사진게시물 정보를 저장하는 클래스
 * 
 * @author 박상현
 * @since 2023. 10. 24.
 */
public class PhotoBoardGetDTO {

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
	/** userLike : 사용자 추천 여부 */
	private Integer userLike;
	/** file : 게시글 파일 정보 */
	private List<FileVO> file;

	public Integer getUserLike() {
		return userLike;
	}

	public void setUserLike(Integer userLike) {
		this.userLike = userLike;
	}

	public Integer getIndex() {
		return index;
	}

   public void setUserLike(Integer userLike) {
      this.userLike = userLike;
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
   
   public List<FileVO> getFile() {
      return file;
   }

   public void setFile(List<FileVO> file) {
      this.file = file;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("PhotoBoardGetDTO [index=");
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
      builder.append(", userLike=");
      builder.append(userLike);
      builder.append(", file=");
      builder.append(file);
      builder.append("]");
      return builder.toString();
   }

}