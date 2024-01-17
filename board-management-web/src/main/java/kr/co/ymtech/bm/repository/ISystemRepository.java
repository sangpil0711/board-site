package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.SystemVO;

public interface ISystemRepository {

	public Integer createSystem(SystemVO system);
	
	public List<SystemVO> findPage();

}
