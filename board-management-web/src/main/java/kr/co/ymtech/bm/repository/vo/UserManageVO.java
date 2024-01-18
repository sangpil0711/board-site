package kr.co.ymtech.bm.repository.vo;

public class UserManageVO {
	
	/** id : 유저 아이디 */
	private String id;
	/** username : 유저 이름 */
	private String username;
	/** email : 유저 이메일 */
	private String email;
	/** gradeId : 권한 번호 */
	private Integer gradeId;
	
	public UserManageVO() {
		super();
	}

	public UserManageVO(String id, String username, String email, Integer gradeId) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.gradeId = gradeId;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserManageDTO [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append(", gradeId=");
		builder.append(gradeId);
		builder.append("]");
		return builder.toString();
	}

}