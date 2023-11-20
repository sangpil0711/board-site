package kr.co.ymtech.bm.controller.dto;

import java.util.List;

/**
 * 파일 정보를 저장하는 클래스
 * 
 * @author 박상현
 * @since 2023. 11. 17.
 */
public class FileDTO {

	/** name : 파일 이름 */
	private String name;
	/** isDirectory : 디렉토리(폴더) */
	private Boolean isDirectory;
	/** Path : 파일 경로 */
	private String Path;
	private List<FileDTO> child;

	public List<FileDTO> getChild() {
		return child;
	}

	public void setChild(List<FileDTO> child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDirectory() {
		return isDirectory;
	}

	public void setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDTO [name=");
		builder.append(name);
		builder.append(", isDirectory=");
		builder.append(isDirectory);
		builder.append(", Path=");
		builder.append(Path);
		builder.append(", child=");
		builder.append(child);
		builder.append("]");
		return builder.toString();
	}

}
