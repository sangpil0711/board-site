package kr.co.ymtech.bm.repository.vo;

public class InfoVO {

	/** id : 사용자 아이디 */
	private String id;
	/** username : 사용자 이름 */
	private String username;
	/** email : 사용자 이메일 */
	private String email;
	
	public InfoVO() {
		super();
	}

	public InfoVO(String id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
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

	public void setId(String id) {
		this.id = id;
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
		builder.append("InfoVO [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
