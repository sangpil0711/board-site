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

		// UserDetailsService에서 사용자 정보를 가져옴
//        UserDetails userDetails = loadUserByUsername((String) principal);
//
//        if (userDetails != null && userDetails.getPassword().equals(password)) {
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            // 명시적으로 타입 변환
//            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
//            return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorities);
//        } else {
//            return null;
//        }
	

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
