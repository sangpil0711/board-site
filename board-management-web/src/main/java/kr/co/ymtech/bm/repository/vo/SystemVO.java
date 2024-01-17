package kr.co.ymtech.bm.repository.vo;

public class SystemVO {

	/** key : 시스템 프로퍼티 키 */
	private String key;
	/** value : 키에 대한 값 */
	private String value;
	/** description : 키에 대한 설명 */
	private String description;
	/** createDate : 생성 날짜 */
	private Long createDate;
	/** updateDate : 수정 날짜 */
	private Long updateDate;

	public SystemVO() {                       
		super();
	}

	public SystemVO(String key, String value, String description) {
		this.key = key;
		this.value = value;
		this.description = description;
	}
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public Long getUpdateDate() {
		return updateDate;
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

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
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
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append("]");
		return builder.toString();
	}

}
