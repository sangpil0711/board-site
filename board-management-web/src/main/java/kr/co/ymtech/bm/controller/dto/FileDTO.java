package kr.co.ymtech.bm.controller.dto;

public class FileDTO {

	private String name;
	private Boolean isDirectory;
	private String Path;
	private Integer depth;

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
	
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
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
		builder.append(", depth=");
		builder.append(depth);
		builder.append("]");
		return builder.toString();
	}

}
