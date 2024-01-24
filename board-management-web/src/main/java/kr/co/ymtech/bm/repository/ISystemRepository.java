package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.SystemVO;

public interface ISystemRepository {

	/**
	 * @Method createSystem 시스템을 생성하는 메소드
	 *
	 * @param system 생성할 시스템 정보
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	public Integer createSystem(SystemVO system);
	
	/**
	 * @Method findPage 시스템 정보를 조회하는 메소드
	 *
	 * @param pageNumber 페이지 번호
	 * @param itemSize 페이지 당 아이템 수
	 *
	 * @author 황상필
	 * @since 2024. 01. 17.
	 */
	public List<SystemVO> findPage(Integer pageNumber, Integer itemSize);
	
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
	public Integer updateSystem(SystemVO system);
	
	/**
	 * @Method findCount 시스템 수를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2024. 01. 18.
	 */
	public Integer findCount();
	
	/**
	 * @Method checkKey 키 중복여부를 확인하는 메소드
	 *
	 * @param key 확인할 키
	 * 
	 * @author 황상필
	 * @since 2024. 01. 24.
	 */
	public Integer checkKey(String key);

}
