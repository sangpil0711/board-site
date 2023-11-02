package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

/**
 * 사진게시글 정보, 총 게시글 수, 게시글 검색을 포함하는 클래스
 * 
 * @author 박상현
 * @since 2023. 10. 31
 */
public class PhotoBoardPageDTO {

	private List<PhotoBoardVO> photoBoardList;
    private Integer totalCount;
    
    /**
     * @Method getPhotoBoardList : PhotoBoardVO를 반환하는 메소드
     *
     * @return PhotoBoardVO에서 받은 photoBoardList 반환
     *
     * @author 박상현
     * @since 2023. 10. 31
     */
    public List<PhotoBoardVO> getPhotoBoardList() {
        return photoBoardList;
    }

    /**
     * @Method setPhotoBoardList : PhotoBoardVO를 설정하는 메소드
     *
     * @param photoBoardList 설정할 PhotoBoardVO
     *
     * @author 박상현
     * @since 2023. 10. 31
     */
    public void setPhotoBoardList(List<PhotoBoardVO> photoBoardList) {
        this.photoBoardList = photoBoardList;
    }
    
    /**
     * 
     * @Method getTotalCount : 전체게시물 수를 반환하는 메소드
     *
     * @return 전체 게시물 수를 반환
     *
     * @author 박상현
     * @since 2023. 10. 31
     */
    public Integer getTotalCount() {
        return totalCount;
    }
    
    /**
     * 
     * @Method setTotalCount : 전체게시물 수를 반환하는 메소드
     *
     * @param totalCount : 전체 게시물 수를 설정
     *
     * @author 박상현
     * @since 2023. 10. 31
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
	/**
	 * Method : 객체(게시글 항목)를 문자열로 변환하는 메서드
	 * 
	 * @return : 문자열을 반환
	 * 
	 * @author 박상현
	 * @since  2023.10. 31
	 */
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhotoBoardPageDTO [photoBoardList=");
		builder.append(photoBoardList);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}
    
}
