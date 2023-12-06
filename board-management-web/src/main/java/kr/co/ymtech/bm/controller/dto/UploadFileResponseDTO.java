package kr.co.ymtech.bm.controller.dto;

import java.util.List;

public class UploadFileResponseDTO {

	private Integer successCount;

	private Integer failCount;

	private List<String> successFileNames;


	public List<String> getSuccessFileNames() {
		return successFileNames;
	}

	public void setSuccessFileNames(List<String> successFileNames) {
		this.successFileNames = successFileNames;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadFileResponseDTO [successCount=");
		builder.append(successCount);
		builder.append(", failCount=");
		builder.append(failCount);
		builder.append(", successFileNames=");
		builder.append(successFileNames);
		builder.append("]");
		return builder.toString();
	}
}
