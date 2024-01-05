package kr.co.ymtech.bm.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 로그인 CustomAuthenticationFilter 클래스
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public CustomAuthenticationFilter() {
		super();
	}

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	/**
	 * Method : 로그인 시도를 처리하는 메소드
	 * 
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 *
	 * @author 박상현
	 * @since 2024. 1. 5.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		// 사용자가 제출한 로그인 요청에서 username과 password를 추출하여 인증 토큰 생성
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				request.getParameter("username"), request.getParameter("password"));
		// 부가적인 인증 정보를 설정
		setDetails(request, authRequest);
		// AuthenticationManager를 사용하여 생성한 인증 토큰을 인증
		return this.getAuthenticationManager().authenticate(authRequest);
	}

}