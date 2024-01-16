package kr.co.ymtech.bm.service;

import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.UpdateUserInformationDTO;
import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.repository.IUpdateUserInformationRepository;
import kr.co.ymtech.bm.repository.UpdateUserInformationRepository;

@Service
public class UpdateUserInformationService implements IUpdateUserInformationService {

	private final IUpdateUserInformationRepository userInformationUpdateRepository;

	public UpdateUserInformationService(UpdateUserInformationRepository userInformationUpdateRepository) {
		this.userInformationUpdateRepository = userInformationUpdateRepository;
	}

	@Override
	public UserDTO findUserInformation(String id) {

		UserDTO userInformation = userInformationUpdateRepository.findUserInformation(id);
		

		return userInformation;
	}
	
	@Override
	public Integer updateUserInformation(UpdateUserInformationDTO userInformation) {
		
		Integer updateInformation = userInformationUpdateRepository.updateUserInformation(userInformation);
		
		return updateInformation;
	}

}
