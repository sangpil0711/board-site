package kr.co.ymtech.bm.controller.dto;

public class UserDTO {

	/** id : 사용자 아이디 */
	private String id;
	/** password : 사용자 비밀번호 */
	private String password;
	/** password : 사용자 비밀번호확인 */
	private String passwordCheck;
	/** username : 사용자 이름 */
	private String username;
	/** email : 사용자 이메일 */
	private String email;
	/** createDate : 사용자 가입일 */
	private Long createDate;
	/** gradeId : 권한 번호 */
	private Integer gradeId;

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPasswordCheck() {
		return passwordCheck;
	}

	public String getUsername() {
		return username;
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

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
		builder.append("UserDTO [id=");
		builder.append(id);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordCheck=");
		builder.append(passwordCheck);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", gradeId=");
		builder.append(gradeId);
		builder.append("]");
		return builder.toString();
	}

}
