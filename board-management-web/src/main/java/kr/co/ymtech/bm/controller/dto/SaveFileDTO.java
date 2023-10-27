package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SaveFileDTO {
	private List<MultipartFile> files;

	/**
	 * @return the files
	 */
	public List<MultipartFile> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	/**
	 * @Method toString
	 *
	 * @see java.lang.Object#toString()
	 *
	 * @return
	 *
	 * @author 황상필
	 * @since 2023. 10. 23.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveFileDTO [files=");
		builder.append(files);
		builder.append("]");
		return builder.toString();
	}

	
	
}
