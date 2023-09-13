package kr.co.ymtech.bm.Service;

import java.util.List;
import kr.co.ymtech.bm.controller.dto.BoardGetDTO;

public interface IBoardService {

	// 모든 게시물 조회
	public List<BoardGetDTO> findAll();

	// 게시물 저장
	Integer boardSave(BoardGetDTO board);

	// 게시물 수정
	Integer boardUpdate(BoardGetDTO board);

	// 게시물 삭제
	Integer boardDelete(Integer index);

	// 특정 인덱스의 게시물 조회
	public List<BoardGetDTO> indexSearch(Integer index);

}
