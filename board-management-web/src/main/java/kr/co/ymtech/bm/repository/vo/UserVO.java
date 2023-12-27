package kr.co.ymtech.bm.repository.vo;

public class UserVO {

	private String id;
	private String password;
	private String username;
	private String email;
	private Long createDate;
	private Integer gradeId;
	private String gradeName;
	private String desc;

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getGradeName() {
		return gradeName;
	}

	public String getDesc() {
		return desc;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEmail() {
		return email;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", gradeId=");
		builder.append(gradeId);
		builder.append(", gradeName=");
		builder.append(gradeName);
		builder.append(", desc=");
		builder.append(desc);
		builder.append("]");
		return builder.toString();
	}
}
