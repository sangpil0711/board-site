package kr.co.ymtech.bm.controller.dto;

import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.FileVO;

public class BoardFileDTO {
	
	private BoardVO board;
	private FileVO file;
	
	/**
	 * @return the board
	 */
	public BoardVO getBoard() {
		return board;
	}
	
	/**
	 * @param board the board to set
	 */
	public void setBoard(BoardVO board) {
		this.board = board;
	}
	
	/**
	 * @return the file
	 */
	public FileVO getFile() {
		return file;
	}
	
	/**
	 * @param file the file to set
	 */
	public void setFile(FileVO file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardFileDTO [board=");
		builder.append(board);
		builder.append(", file=");
		builder.append(file);
		builder.append("]");
		return builder.toString();
	}
	
}
