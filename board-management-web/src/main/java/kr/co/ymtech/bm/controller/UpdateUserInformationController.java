package kr.co.ymtech.bm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.UpdateUserInformationDTO;
import kr.co.ymtech.bm.controller.dto.UserDTO;
import kr.co.ymtech.bm.service.UpdateUserInformationService;

@RestController
public class UpdateUserInformationController {

	private final UpdateUserInformationService userInformationUpdateService;

	public UpdateUserInformationController(UpdateUserInformationService userInformationUpdateService) {
		this.userInformationUpdateService = userInformationUpdateService;
	}

	@GetMapping(value = "/updateInformation/{id}")
	public UserDTO findUserInformation(@PathVariable String id) {

		UserDTO userInformation = userInformationUpdateService.findUserInformation(id);

		return userInformation;
	}

	@PatchMapping(value = "/updateInformation/update")
	public Integer UpdateUserInformation(@RequestBody UpdateUserInformationDTO userInformation) {
		
		Integer updateInformation = userInformationUpdateService.updateUserInformation(userInformation);
		
		return updateInformation;
	}

}
