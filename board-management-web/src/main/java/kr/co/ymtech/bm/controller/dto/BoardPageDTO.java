package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.PageVO;
import kr.co.ymtech.bm.repository.vo.SearchVO;

public class BoardPageDTO {
    private List<BoardVO> list;
    private List<PageVO> totalCount;
    private List<SearchVO> searchVO;

    /**
     * @return the boardList
     */
    public List<BoardVO> getBoardList() {
        return list;
    }

    /**
     * @param boardList the boardList to set
     */
    public void setBoardList(List<BoardVO> boardList) {
        this.list = boardList;
    }

    /**
     * @return the pageList
     */
    public List<PageVO> getPageList() {
        return totalCount;
    }

    /**
     * @param pageList the pageList to set
     */
    public void setPageList(List<PageVO> pageList) {
        this.totalCount = pageList;
    }

    /**
     * @return the searchVO
     */
    public List<SearchVO> getSearchVO() {
        return searchVO;
    }

    /**
     * @param searchVO the searchVO to set
     */
    public void setSearchVO(List<SearchVO> searchVO) {
        this.searchVO = searchVO;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BoardPageDTO [list=");
        builder.append(list);
        builder.append(", totalCount=");
        builder.append(totalCount);
        builder.append(", searchVO="); // 추가된 SearchVO 필드
        builder.append(searchVO); // 추가된 SearchVO 필드
        builder.append("]");
        return builder.toString();
    }
}