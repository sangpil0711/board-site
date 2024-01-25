package kr.co.ymtech.bm.controller.dto;

public class FileSetDTO {

	/** imageType : 이미지 유형 */
	private String fileType;
	/** imageMaxSize : 이미지 최대 용량 */
	private Integer fileMaxSize;

	public String getFileType() {
		return fileType;
	}

	public Integer getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFileMaxSize(Integer fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileSetDTO [fileType=");
		builder.append(fileType);
		builder.append(", fileMaxSize=");
		builder.append(fileMaxSize);
		builder.append("]");
		return builder.toString();
	}
	
}
