package kr.co.ymtech.bm.repository;

public interface IFileRepository {
	
	/**
	 * @Method resetFiles 게시물에 업로드된 파일을 초기화 시키는 메소드
	 *
	 * @param index 해당 게시글 번호
	 * 
	 * @author 황상필
	 * @since 2023. 10. 25.
	 */
	public Integer deleteFiles(Integer index);
	
}
