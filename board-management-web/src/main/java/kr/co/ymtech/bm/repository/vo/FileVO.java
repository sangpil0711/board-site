package kr.co.ymtech.bm.repository.vo;

/**
 * 파일 id, 게시판 번호, 파일 위치, 파일 이름을 포함하는 클래스
 * 
 * @author 황상필
 * @since 2023. 10. 23
 */
public class FileVO {
	
	/** fileId : 파일 uuid */
	private String fileId;
	/** boardIndex : 게시글 번호 */
	private Integer boardIndex;
	/** filePath : 파일 경로 */
	private String filePath;
	/** fileName : 파일 이름 */
	private String fileName;
	/** fileSize : 파일 크기 */
	private Long fileSize;
	
	/**
	 * @Method : 기본 생성자
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	public FileVO() {                       
		super();
	}
	
	/**
	 * @Method 모든 필드를 초기화하는 생성자
	 * 
	 * @param fileId 파일 uuid
	 * @param boardIndex 게시글 번호
	 * @param filePath 파일 위치
	 * @param fileName 파일 이름
	 * @param fileSize 파일 크기
	 * 
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	public FileVO(String fileId, Integer boardIndex, String filePath, String fileName, Long fileSize) {
		this.fileId = fileId;
		this.boardIndex = boardIndex;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getBoardIndex() {
		return boardIndex;
	}

	public void setBoardIndex(Integer boardIndex) {
		this.boardIndex = boardIndex;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append("]");
		return builder.toString();
	}
	
}
