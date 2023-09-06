package kr.co.ymtech.bm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ymtech.bm.repository.IBoardRepository;

@Service
public class BoardService {
	
	private final IBoardRepository IboardRepository;

	@Autowired
	private BoardService(IBoardRepository IboardRepository) {
		this.IboardRepository = IboardRepository;
	}
	


}
