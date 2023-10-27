package kr.co.ymtech.bm.repository.vo;

/**
 * 파일 id, 게시판 번호, 파일 위치, 파일 이름을 포함하는 클래스
 */
public class FileVO {
	
	private String fileId;
	private Integer boardIndex;
	private String filePath;
	private String fileName;
	
	public FileVO() {                       
		super();
	}
	
	public FileVO(String fileId, Integer boardIndex, String filePath, String fileName) {
		this.fileId = fileId;
		this.boardIndex = boardIndex;
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	/**
	 * @Method getFileId fileId를 반환하는 메소드
	 *
	 * @return fileId를 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public String getFileId() {
		return fileId;
	}
	
	/**
	 * @Method setFileId fileId를 설정하는 메소드
	 * 
	 * @param fileId 설정할 fileId
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * @Method getBoardIndex boardIndex를 반환하는 메소드
	 *
	 * @return boardIndex를 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public Integer getBoardIndex() {
		return boardIndex;
	}
	
	/**
	 * @Method setBoardIndex boardIndex를 설정하는 메소드
	 * 
	 * @param boardIndex 설정할 boardIndex
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public void setBoardIndex(Integer boardIndex) {
		this.boardIndex = boardIndex;
	}
	
	/**
	 * @Method getFilePath filePath를 반환하는 메소드
	 *
	 * @return filePath를 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * @Method setFilePath filePath를 설정하는 메소드
	 * 
	 * @param filePath 설정할 filePath
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * @Method getFileName fileName를 반환하는 메소드
	 *
	 * @return fileName를 반환
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * @Method setFileName fileName를 설정하는 메소드
	 * 
	 * @param fileName 설정할 fileName
	 *
	 * @author 황상필
	 * @since 2023. 10. 10.
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
