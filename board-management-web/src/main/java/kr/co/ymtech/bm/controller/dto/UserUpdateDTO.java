package kr.co.ymtech.bm.controller.dto;

public class UserUpdateDTO {

	/** currentPassword : 현재비밀번호 */
	private String currentPassword;
	/** newPassword : 새 비밀번호 */
	private String newPassword;
	/** newPasswordCheck : 새 비밀번호 확인 */
	private String newPasswordCheck;
	/** username : 유저 이름 */
	private String username;
	/** email : 유저 이메일 */
	private String email;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getNewPasswordCheck() {
		return newPasswordCheck;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setNewPasswordCheck(String newPasswordCheck) {
		this.newPasswordCheck = newPasswordCheck;
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
		builder.append("UserUpdateDTO [currentPassword=");
		builder.append(currentPassword);
		builder.append(", newPassword=");
		builder.append(newPassword);
		builder.append(", newPasswordCheck=");
		builder.append(newPasswordCheck);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
