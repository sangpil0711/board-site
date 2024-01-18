package kr.co.ymtech.bm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	/**
	 * @Method createSystem 시스템을 생성하는 메소드
	 *
	 * @param system 생성할 시스템 정보
	 * 
	 * @return systemData를 ResponseEntity에 담아서 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@PostMapping(value = "/createSystem")
	public ResponseEntity<Integer> createSystem(@RequestBody SystemDTO system) {

		Integer systemData = systemService.createSystem(system);

		return new ResponseEntity<Integer>(systemData, HttpStatus.OK);
	}

	/**
	 * @Method findPage 시스템 정보를 조회하는 메소드
	 *
	 * @param pageNumber 페이지 번호
	 * @param itemSize 페이지 당 아이템 수
	 * 
	 * @return systemList를 ReponseEntity에 담아서 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@GetMapping(value = "/systemPage")
	public ResponseEntity<SystemPageDTO> findPage(Integer pageNumber, Integer itemSize) {

		SystemPageDTO systemList = systemService.findPage(pageNumber, itemSize);

		return new ResponseEntity<SystemPageDTO>(systemList, HttpStatus.OK);
	}

	/**
	 * @Method deleteSystem 시스템을 삭제하는 메소드
	 *
	 * @param key 삭제할 시스템 키
	 * 
	 * @return deleteData를 ResponseEntity에 담아서 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@DeleteMapping(value = "/deleteSystem")
	public ResponseEntity<Integer> deleteSystem(@RequestParam String key) {

		Integer deleteData = systemService.deleteSystem(key);

		return new ResponseEntity<Integer>(deleteData, HttpStatus.OK);
	}

	/**
	 * @Method updateSystem 시스템을 수정하는 메소드
	 *
	 * @param system 수정할 시스템
	 * 
	 * @return updateData를 ResponseEntity에 담아서 반환
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@PatchMapping(value = "/updateSystem")
	public ResponseEntity<Integer> updateSystem(@RequestBody SystemDTO system) {

		Integer updateData = systemService.updateSystem(system);

		return new ResponseEntity<Integer>(updateData, HttpStatus.OK);
	}

}
