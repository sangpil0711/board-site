package kr.co.ymtech.bm.controller.dto;

/**
 * 게시물 정보를 저장하는 클래스
 * 
 * @author 황상필
 * @since 2023. 10. 30.
 */
public class PageDTO {

	/** postPerPage : 페이지당 표시되는 게시글 수 */
	private String postPerPage;
	/** maxPage : 한 번에 표시되는 최대 페이지 수 */
	private Integer maxPage;

	public String getPostPerPage() {
		return postPerPage;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setPostPerPage(String postPerPage) {
		this.postPerPage = postPerPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageDTO [postPerPage=");
		builder.append(postPerPage);
		builder.append(", maxPage=");
		builder.append(maxPage);
		builder.append("]");
		return builder.toString();
	}
}
