package kr.co.ymtech.bm.repository.vo;

public class PageVO {
	
	/** index : 게시글 번호 */
	private Integer totalCount;

	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageVO [totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}
	
}