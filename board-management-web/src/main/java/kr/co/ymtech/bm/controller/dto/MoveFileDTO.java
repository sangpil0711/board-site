package kr.co.ymtech.bm.controller.dto;

public class MoveFileDTO {

	/** fileName : 이동한 파일 이름 */
	private String fileName;
	/** oldPath : 이동한 파일 경로 */
	private String oldPath;
	/** newPath : 이동할 폴더 경로 */
	private String newPath;

	public String getFileName() {
		return fileName;
	}

	public String getOldPath() {
		return oldPath;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoveFileDTO [fileName=");
		builder.append(fileName);
		builder.append(", oldPath=");
		builder.append(oldPath);
		builder.append(", newPath=");
		builder.append(newPath);
		builder.append("]");
		return builder.toString();
	}
	
}
