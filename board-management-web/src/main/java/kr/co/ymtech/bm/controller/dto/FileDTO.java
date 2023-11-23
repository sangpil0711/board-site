package kr.co.ymtech.bm.controller.dto;

import java.util.List;

public class FileDTO {

	private String name;
	private Boolean isDirectory;
	private String Path;
	private Boolean folderState;
	private List<FileDTO> child;

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
		this.Path = path;
	}
	
	public Boolean getFolderState() {
		return folderState;
	}

	public void setFolderState(Boolean folderState) {
		this.folderState = folderState;
	}
	
	public List<FileDTO> getChild() {
		return child;
	}

	public void setChild(List<FileDTO> child) {
		this.child = child;
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
		builder.append(", folderState=");
		builder.append(folderState);
		builder.append(", child=");
		builder.append(child);
		builder.append("]");
		return builder.toString();
	}

}
