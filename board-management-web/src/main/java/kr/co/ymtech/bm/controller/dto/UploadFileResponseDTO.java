package kr.co.ymtech.bm.controller.dto;

import java.util.List;

public class UploadFileResponseDTO {

	/** successCount: 파일 업로드 성공 횟수 */
	private Integer successCount;
	/** failCount: 파일 업로드 실패 횟수 */
	private Integer failCount;
	/** error: 서버 에러 메세지 */
	private String errorMessage;
	/** successFileNames: 성공 파일 이름 */
	private List<String> successFileNames;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

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
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", successFileNames=");
		builder.append(successFileNames);
		builder.append("]");
		return builder.toString();
	}
}
