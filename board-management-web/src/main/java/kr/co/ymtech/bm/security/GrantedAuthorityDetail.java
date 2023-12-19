package kr.co.ymtech.bm.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import kr.co.ymtech.bm.security.model.UserGrade;

public class GrantedAuthorityDetail implements GrantedAuthority {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final UserGrade grade;

	private User user;

	public GrantedAuthorityDetail(UserGrade grade) {
		this.grade = grade;
	}

	@Override
	public String getAuthority() {
		return this.grade.getName();
	}

	public UserGrade getGrade() {
		return grade;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}