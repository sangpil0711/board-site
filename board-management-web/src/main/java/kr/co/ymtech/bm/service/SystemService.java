package kr.co.ymtech.bm.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.SystemDTO;
import kr.co.ymtech.bm.controller.dto.SystemPageDTO;
import kr.co.ymtech.bm.repository.ISystemRepository;
import kr.co.ymtech.bm.repository.SystemRepository;
import kr.co.ymtech.bm.repository.vo.SystemVO;

@Service
public class SystemService implements ISystemService {

	private final ISystemRepository systemRepository;

	public SystemService(SystemRepository systemRepository) {
		this.systemRepository = systemRepository;
	}

	/**
	 * @Method createSystem 시스템을 생성하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.ISystemService#createSystem(kr.co.ymtech.bm.controller.dto.SystemDTO)
	 *
	 * @param system 생성할 시스템 정보
	 * 
	 * @return 시스템 정보를 반환하는 repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public Integer createSystem(SystemDTO system) {
		
		SystemVO vo = new SystemVO();
		
		// key, value, description 중 하나라도 null값일 경우 동작
		if (system.getKey() == null || system.getValue() == null || system.getDescription() == null) {
			throw new IllegalArgumentException("입력하지 않은 항목이 있습니다.");
		} else {
			vo.setKey(system.getKey());
			vo.setValue(system.getValue());
			vo.setDescription(system.getDescription());
			vo.setCreateDate(new Date().getTime());
		}
		
		return systemRepository.createSystem(vo);
	}
	
	/**
	 * @Method findPage 시스템 정보를 조회하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.ISystemService#findPage(java.lang.Integer, java.lang.Integer)
	 *
	 * @param pageNumber 페이지 번호
	 * @param itemSize 페이지 당 아이템 개수
	 * 
	 * @return 시스템 리스트와 리스트의 총 개수를 반환하는 repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public SystemPageDTO findPage(Integer pageNumber, Integer itemSize) {
		
		List<SystemVO> systemList = systemRepository.findPage(pageNumber, itemSize);
		Integer totalCount = systemRepository.findCount();
		
		SystemPageDTO systemPage = new SystemPageDTO();
		systemPage.setSystemList(systemList);
		systemPage.setTotalCount(totalCount);
		
		return systemPage;
	}
	
	/**
	 * 
	 * @Method deleteSystem 시스템을 삭제하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.ISystemService#deleteSystem(java.lang.String)
	 *
	 * @param key 삭제할 시스템 키
	 * 
	 * @return 시스템을 삭제하는 repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public Integer deleteSystem(String key) {
		return systemRepository.deleteSystem(key);
	}
	
	/**
	 * @Method updateSystem 시스템을 수정하는 메소드
	 *
	 * @see kr.co.ymtech.bm.service.ISystemService#updateSystem(kr.co.ymtech.bm.controller.dto.SystemDTO)
	 *
	 * @param system 수정할 시스템
	 * 
	 * @return 시스템을 수정하는 repository 함수 실행
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	@Override
	public Integer updateSystem(SystemDTO system) {
		
		SystemVO vo = new SystemVO();
		
		// value와 description 중 하나라도 null값일 경우 동작
		if (system.getValue() == null || system.getDescription() == null) {
			throw new IllegalArgumentException("입력하지 않은 항목이 있습니다.");
		} else {
			vo.setKey(system.getKey());
			vo.setValue(system.getValue());
			vo.setDescription(system.getDescription());
			vo.setUpdateDate(new Date().getTime());
		}
		
		return systemRepository.updateSystem(vo);
	}

}
