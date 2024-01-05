package kr.co.ymtech.bm.repository.vo;

public class UserVO {

	/** id : 사용자 아이디 */
	private String id;
	/** password : 사용자 비밀번호 */
	private String password;
	/** username : 사용자 이름 */
	private String username;
	/** email : 사용자 이메일 */
	private String email;
	/** createDate : 사용자 가입일 */
	private Long createDate;
	/** gradeId : 권한 번호 */
	private Integer gradeId;
	/** name : 권한 이름 */
	private String name;
	/** description : 권한 설명 */
	private String description;

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
