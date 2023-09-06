package kr.co.ymtech.bm.repository;

import java.util.List;


public interface IBoardRepository {

	List<BoardVO> findAll();
	
	Integer save(BoardVO board);

}
