package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PhotoBoardUpdateDTO {
	
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
		/** addFiles : 추가된 첨부파일 */
		private List<MultipartFile> addFiles;
		/**	deleteFiles : 삭제된 첨부파일 */
		private List<String> deleteFiles;

		public Integer getIndex() {
			return index;
		}

		public String getTitle() {
			return title;
		}

		public String getText() {
			return text;
		}

		public String getUserId() {
			return userId;
		}

		public Integer getCategory() {
			return category;
		}

		public Long getCreateDate() {
			return createDate;
		}

		public List<MultipartFile> getAddFiles() {
			return addFiles;
		}

		public List<String> getDeleteFiles() {
			return deleteFiles;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setText(String text) {
			this.text = text;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public void setCategory(Integer category) {
			this.category = category;
		}

		public void setCreateDate(Long createDate) {
			this.createDate = createDate;
		}

		public void setAddFiles(List<MultipartFile> addFiles) {
			this.addFiles = addFiles;
		}

		public void setDeleteFiles(List<String> deleteFiles) {
			this.deleteFiles = deleteFiles;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("PhotoBoardUpdateDTO [index=");
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

