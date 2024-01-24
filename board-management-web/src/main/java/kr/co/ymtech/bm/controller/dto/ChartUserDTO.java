package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.UserVO;

public class ChartUserDTO {

	List<UserVO> userList;

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChartUserDTO [userList=");
		builder.append(userList);
		builder.append("]");
		return builder.toString();
	}

}
