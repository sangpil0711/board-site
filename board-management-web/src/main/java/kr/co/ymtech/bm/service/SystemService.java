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

	@Override
	public Integer createSystem(SystemDTO system) {
		
		SystemVO vo = new SystemVO();
		vo.setKey(system.getKey());
		vo.setValue(system.getValue());
		vo.setDescription(system.getDescription());
		vo.setCreateDate(new Date().getTime());
		
		return systemRepository.createSystem(vo);
	}
	
	@Override
	public SystemPageDTO findPage() {
		
		List<SystemVO> systemList = systemRepository.findPage();
		
		SystemPageDTO systemPage = new SystemPageDTO();
		systemPage.setSystemList(systemList);
		
		return systemPage;
	}

}
