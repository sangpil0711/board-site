package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.UpdateUserInformationDTO;
import kr.co.ymtech.bm.controller.dto.UserDTO;

public interface IUpdateUserInformationService {

	public UserDTO findUserInformation(String id);

	public Integer updateUserInformation(UpdateUserInformationDTO userInformation);

}
