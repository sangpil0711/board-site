package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;

/**
 * 게시글 정보, 총 게시글 수, 게시글 검색을 포함하는 클래스
 * 
 * @author 황상필
 * @since 2023. 10. 05.
 */
public class BoardPageDTO {
	
	/** list : 게시글 정보 */
    private List<BoardVO> boardList;
    /** totalCount : 총 게시글 수 */
    private Integer totalCount;

    public List<BoardVO> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BoardVO> boardList) {
        this.boardList = boardList;
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
		builder.append("BoardPageDTO [boardList=");
		builder.append(boardList);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append("]");
		return builder.toString();
	}
    
}