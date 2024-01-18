package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.SystemDTO;
import kr.co.ymtech.bm.controller.dto.SystemPageDTO;

public interface ISystemService {

	/**
	 * @Method createSystem 시스템을 생성하는 메소드
	 *
	 * @param system 생성할 시스템 정보
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	public Integer createSystem(SystemDTO system);
	
	/**
	 * @Method findPage 시스템 정보를 조회하는 메소드
	 *
	 * @param pageNumber 페이지 번호
	 * @param itemSize 페이지 당 아이템 수
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	public SystemPageDTO findPage(Integer pageNumber, Integer itemSize);
	
	/**
	 * @Method deleteSystem 시스템을 삭제하는 메소드
	 *
	 * @param key 삭제할 시스템 키
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	public Integer deleteSystem(String key);
	
	/**
	 * @Method updateSystem 시스템을 수정하는 메소드
	 *
	 * @param system 수정할 시스템
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	public Integer updateSystem(SystemDTO system);

}
