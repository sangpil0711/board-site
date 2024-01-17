package kr.co.ymtech.bm.service;

import kr.co.ymtech.bm.controller.dto.SystemDTO;
import kr.co.ymtech.bm.controller.dto.SystemPageDTO;

public interface ISystemService {

	public Integer createSystem(SystemDTO system);
	
	public SystemPageDTO findPage();

}
