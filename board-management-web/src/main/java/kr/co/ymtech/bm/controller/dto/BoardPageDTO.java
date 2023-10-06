package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.PageVO;

/**
 * 게시글 정보, 총 게시글 수, 게시글 검색을 포함하는 클래스
 */
public class BoardPageDTO {
	
    private List<BoardVO> list;
    private List<PageVO> totalCount;

    /**
     * Method : BoardVO를 반환하는 메소드
     * 
     * @return : BoardVO를 할당받은 list 반환
     */
    public List<BoardVO> getBoardList() {
        return list;
    }

    /**
     * Method : BoardVO를 설정하는 메소드
     * 
     * @param boardList : 설정할 BoardVO
     * 
     * @return : BoardVO 값 설정
     */
    public void setBoardList(List<BoardVO> boardList) {
        this.list = boardList;
    }

    /**
     * Method : PageVO를 반환하는 메소드
     * 
     * @return : PageVO를 할당받은 totalCount 반환
     */
    public List<PageVO> getPageList() {
        return totalCount;
    }

    /**
     * Method : PageVO를 설정하는 메소드
     * 
     * @param pageList : 설정할 PageVO
     * 
     * @return : PageVO 값 설정
     */
    public void setPageList(List<PageVO> pageList) {
        this.totalCount = pageList;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BoardPageDTO [list=");
        builder.append(list);
        builder.append(", totalCount=");
        builder.append(totalCount);
        builder.append("]");
        return builder.toString();
    }
}