package kr.co.ymtech.bm.repository;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

public interface IFileRepository {
	
	/**
	 * @Method saveFile 파일 정보를 저장하는 메소드
	 *
	 * @param file DB에 보낼 파일 데이터
	 *
	 * @author 황상필
	 * @since 2023. 10. 11.
	 */
	public Integer saveFile(FileVO file);
	
	/**
	 * @Method lastBoard 마지막에 저장된 게시물의 번호를 조회하는 메소드
	 *
	 * @author 황상필
	 * @since 2023. 10. 12.
	 */
	public List<BoardVO> lastBoard();
	
}
