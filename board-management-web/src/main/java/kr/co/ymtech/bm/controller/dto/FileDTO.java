package kr.co.ymtech.bm.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class FileDTO {

	private String name;
	private Boolean isDirectory;
	private List<FileDTO> childs;
	private String Path;

	public FileDTO() {
		this.childs = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<FileDTO> getChilds() {
		return childs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChilds(List<FileDTO> childs) {
		this.childs = childs;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDTO [name=");
		builder.append(name);
		builder.append(", childs=");
		builder.append(childs);
		builder.append("]");
		return builder.toString();
	}
}
