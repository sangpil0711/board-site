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

	/** photoBoardList : 게시글 리스트 */
	private List<PhotoBoardVO> photoBoardList;
	/** totalCount : 게시글 총 개수 */
    private Integer totalCount;

    public List<PhotoBoardVO> getPhotoBoardList() {
        return photoBoardList;
    }

    public void setPhotoBoardList(List<PhotoBoardVO> photoBoardList) {
        this.photoBoardList = photoBoardList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

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
