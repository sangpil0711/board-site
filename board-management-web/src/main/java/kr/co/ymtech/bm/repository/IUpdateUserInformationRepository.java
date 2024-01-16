package kr.co.ymtech.bm.repository;

import kr.co.ymtech.bm.controller.dto.UpdateUserInformationDTO;
import kr.co.ymtech.bm.controller.dto.UserDTO;

public interface IUpdateUserInformationRepository {

	public UserDTO findUserInformation(String id);

	public Integer updateUserInformation(UpdateUserInformationDTO userInformation);

}
