package kr.co.ymtech.bm.repository.vo;

public class UserUpdateVO {

	/** id : 유저 아이디 */
	private String id;
	/** newPassword : 새 비밀번호 */
	private String newPassword;
	/** username : 유저 이름 */
	private String username;
	/** email : 유저 이메일 */
	private String email;

	public String getId() {
		return id;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserUpdateVO [id=");
		builder.append(id);
		builder.append(", newPassword=");
		builder.append(newPassword);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
