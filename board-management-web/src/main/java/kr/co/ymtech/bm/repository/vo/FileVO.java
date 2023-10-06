package kr.co.ymtech.bm.repository.vo;

public class FileVO {
	private String fileId;
	private Integer boardIndex;
	private String filePath;
	private String fileName;
	
	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the boardIndex
	 */
	public Integer getBoardIndex() {
		return boardIndex;
	}
	/**
	 * @param boardIndex the boardIndex to set
	 */
	public void setBoardIndex(Integer boardIndex) {
		this.boardIndex = boardIndex;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileVO [fileId=");
		builder.append(fileId);
		builder.append(", boardIndex=");
		builder.append(boardIndex);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
