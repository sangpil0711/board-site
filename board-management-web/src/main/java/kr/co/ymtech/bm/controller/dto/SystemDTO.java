package kr.co.ymtech.bm.controller.dto;

public class SystemDTO {

	/** key : 시스템 프로퍼티 키 */
	private String key;
	/** value : 키에 대한 값 */
	private String value;
	/** description : 키에 대한 설명 */
	private String description;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemDTO [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
