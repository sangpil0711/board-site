package kr.co.ymtech.bm.controller.dto;

public class ImageSetDTO {

	/** imageType : 이미지 유형 */
	private String imageType;
	/** imageMaxSize : 이미지 최대 용량 */
	private Integer imageMaxSize;

	public String getImageType() {
		return imageType;
	}

	public Integer getImageMaxSize() {
		return imageMaxSize;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public void setImageMaxSize(Integer imageMaxSize) {
		this.imageMaxSize = imageMaxSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageSetDTO [imageType=");
		builder.append(imageType);
		builder.append(", imageMaxSize=");
		builder.append(imageMaxSize);
		builder.append("]");
		return builder.toString();
	}
	
}
