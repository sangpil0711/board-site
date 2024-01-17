package kr.co.ymtech.bm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ymtech.bm.controller.dto.SystemDTO;
import kr.co.ymtech.bm.controller.dto.SystemPageDTO;
import kr.co.ymtech.bm.service.ISystemService;
import kr.co.ymtech.bm.service.SystemService;

@RestController
public class SystemController {

	private final ISystemService systemService;

	public SystemController(SystemService systemService) {
		this.systemService = systemService;
	}

	@PostMapping(value = "/createSystem")
	public ResponseEntity<Integer> createSystem(@RequestBody SystemDTO system) {

		Integer systemData = systemService.createSystem(system);

		return new ResponseEntity<Integer>(systemData, HttpStatus.OK);
	}
	
	@GetMapping(value = "/systemPage")
	public ResponseEntity<SystemPageDTO> findPage() {
		
		SystemPageDTO systemList = systemService.findPage();
		
		return new ResponseEntity<SystemPageDTO>(systemList, HttpStatus.OK);
	}

}
