package kr.co.ymtech.bm.repository;

import java.util.List;



public interface IBoardRepository {
	
	// 모든 게시물 조회
	List<BoardVO> findAll();
	
	// 게시물 저장
	Integer boardSave(BoardVO board);
	
	// 게시물 수정
	Integer boardUpdate(BoardVO board);
	
	// 게시물 삭제
	Integer boardDelete(Integer index);

	// 특정 인덱스의 게시물 조회
	List<BoardVO> indexSearch(Integer index);


	



	


	

}
