package kr.co.ymtech.bm.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import kr.co.ymtech.bm.repository.vo.UserVO;
import kr.co.ymtech.bm.security.model.UserGrade;

public class GrantedAuthorityDetail implements GrantedAuthority {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final UserGrade grade;

	private UserVO user;

	private String mapAuthKey;

	private String mapAuthDomain;

	public String getMapAuthKey() {
		return mapAuthKey;
	}

	public void setMapAuthKey(String mapAuthKey) {
		this.mapAuthKey = mapAuthKey;
	}

	public String getMapAuthDomain() {
		return mapAuthDomain;
	}

	public void setMapAuthDomain(String mappAuthDomain) {
		this.mapAuthDomain = mappAuthDomain;
	}

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

	public void setUser(UserVO user) {
		this.user = user;
	}

}
