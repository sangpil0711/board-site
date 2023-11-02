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
	
    private List<BoardVO> boardList;
    private Integer totalCount;

    /**
     * @Method getBoardList BoardVO를 반환하는 메소드
     *
     * @return BoardVO를 할당받은 list 반환
     *
     * @author 황상필
     * @since 2023. 10. 05.
     */
    public List<BoardVO> getBoardList() {
        return boardList;
    }

    /**
     * 
     * @Method setBoardList BoardVO를 설정하는 메소드
     *
     * @param boardList 설정할 BoardVO
     *
     * @author 황상필
     * @since 2023. 10. 05.
     */
    public void setBoardList(List<BoardVO> boardList) {
        this.boardList = boardList;
    }

    /**
     * 
     * @Method getPageList PageVO를 반환하는 메소드
     *
     * @return PageVO를 할당받은 totalCount 반환
     *
     * @author 황상필
     * @since 2023. 10. 05.
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 
     * @Method setPageList PageVO를 설정하는 메소드
     *
     * @param pageList 설정할 PageVO
     *
     * @author 황상필
     * @since 2023. 10. 05.
     */
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