package kr.co.ymtech.bm.controller.dto;

public class UpdateFileDTO {
	
	/** path : 파일 경로 */
	private String path;
	/** name : 변경할 파일 이름 */
	private String name;
	/** newFileName : 변경한 파일 이름 */
	private String newFileName;

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateFileDTO [path=");
		builder.append(path);
		builder.append(", name=");
		builder.append(name);
		builder.append(", newFileName=");
		builder.append(newFileName);
		builder.append("]");
		return builder.toString();
	}
	
}
