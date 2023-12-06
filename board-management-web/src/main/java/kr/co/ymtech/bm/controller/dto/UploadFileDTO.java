package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileDTO {
	
	/** Path : 폴더 경로 */
	private String path;
	/** Name : 폴더 이름 */
	private String name;
	/** files : 파일 데이터 */
	private List<MultipartFile> files;

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileExplorerDTO [Path=");
		builder.append(path);
		builder.append(", Name=");
		builder.append(name);
		builder.append(", files=");
		builder.append(files);
		builder.append("]");
		return builder.toString();
	}

}
