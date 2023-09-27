package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.PageVO;

public class BoardPageDTO {
	private List<BoardVO> list;
	private List<PageVO> totalCount;

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
