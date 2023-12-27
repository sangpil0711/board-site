package kr.co.ymtech.bm.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserVO;
import kr.co.ymtech.bm.security.model.UserGrade;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) {

		Object principal = authentication.getPrincipal();
		Object password = authentication.getCredentials();

		UserVO user = userRepository.findByUsername((String) principal);
		

//      // 명시적으로 타입 변환
//      Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);

		List<GrantedAuthority> list = new ArrayList<>();
		UserGrade grade = UserGrade.getUserGrade(0, "관리자", "관리자 권한");
		GrantedAuthorityDetail detail = new GrantedAuthorityDetail(grade);
		list.add(detail);

		// #1. id, password로 DB에서 조회한 결과로 비교 후 반환 분기
		if (user != null && user.getPassword().equals(password)) {
	        return new UsernamePasswordAuthenticationToken(principal, password, list);
	    } else if (user == null) {
	        throw new UsernameNotFoundException("계정이 존재하지 않습니다.");
	    } else if (!user.getPassword().equals(password)) {
	        throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
	    } else {
	        throw new AuthenticationServiceException("인증 과정 중 문제가 발생했습니다.");
	    }
		
	}

	// UsernamePasswordAuthenticationToken 클래스를 지원하는 메소드
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
