package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SaveFileDTO {
	private List<MultipartFile> files;
	private List<String> fileNames;
	
	/**
	 * @return the files
	 */
	public List<MultipartFile> getFiles() {
		return files;
	}
	/**
	 * @return the fileNames
	 */
	public List<String> getFileNames() {
		return fileNames;
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	/**
	 * @param fileNames the fileNames to set
	 */
	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	/**
	 * @Method toString
	 *
	 * @see java.lang.Object#toString()
	 *
	 * @return
	 *
	 * @author 황상필
	 * @since 2023. 10. 18.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveFileDTO [files=");
		builder.append(files);
		builder.append(", fileNames=");
		builder.append(fileNames);
		builder.append("]");
		return builder.toString();
	}
	
	
}
