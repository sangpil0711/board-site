package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.UserManageVO;

public class UserManagePageDTO {

	/** boardList : 게시글 정보 */
	private List<UserManageVO> userList;
	/** totalCount : 총 게시글 수 */
	private Integer totalCount;

	public List<UserManageVO> getUserList() {
		return userList;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setUserList(List<UserManageVO> userList) {
		this.userList = userList;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserManagePageDTO [userList=");
		builder.append(userList);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}

}
