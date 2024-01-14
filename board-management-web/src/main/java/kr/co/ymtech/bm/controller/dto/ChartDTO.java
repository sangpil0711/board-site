package kr.co.ymtech.bm.controller.dto;

import java.util.List;

/**
 * 차트 정보를 조회하는 클래스
 * 
 * @author 황상필
 * @since 2024. 01. 12.
 */
public class ChartDTO {

	/** countVisitor : 오늘 방문자 수 */
	private Integer countVisitor;
	/** countPost : 오늘 게시글 수 */
	private Integer countPost;
	/** visitorData : 일주일 동안 방문자 수 */
	private List<Integer> visitorData;
	/** postData : 일주일 동안 게시글 수 */
	private List<Integer> postData;

	public Integer getCountVisitor() {
		return countVisitor;
	}

	public void setCountVisitor(Integer countVisitor) {
		this.countVisitor = countVisitor;
	}
	
	public Integer getCountPost() {
		return countPost;
	}

	public void setCountPost(Integer countPost) {
		this.countPost = countPost;
	}
	
	public List<Integer> getVisitorData() {
		return visitorData;
	}

	public void setVisitorData(List<Integer> visitorData) {
		this.visitorData = visitorData;
	}
	
	public List<Integer> getPostData() {
		return postData;
	}

	public void setPostData(List<Integer> postData) {
		this.postData = postData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChartDTO [countVisitor=");
		builder.append(countVisitor);
		builder.append(", countPost=");
		builder.append(countPost);
		builder.append(", visitorData=");
		builder.append(visitorData);
		builder.append(", postData=");
		builder.append(postData);
		builder.append("]");
		return builder.toString();
	}
	
}
