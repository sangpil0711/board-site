package kr.co.ymtech.bm.security;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 *  로그인 CustomLoginFailureHandler 클래스 
 */
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/**
	 *  * Method : 로그인 실패시 오류메세지를 나타나는 함수
	 * 
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 *
	 * @author 황상필
	 * @since 2024. 1. 5.
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 예외에 따른 errorMessage 설정
		String errorMessage;
		if (exception instanceof BadCredentialsException) {
			errorMessage = "비밀번호가 일치하지 않습니다.";
		} else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "계정이 존재하지 않습니다.";
		} else if (exception instanceof AuthenticationServiceException) {
			errorMessage = "인증 과정 중 문제가 발생했습니다. 관리자에게 문의하세요.";
		} else {
			errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
		}
		
		// 로그인 실패 시 입력했던 아이디
		request.getSession().setAttribute("username", request.getParameter("username"));
		
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		
		// 로그인 실패 시 이동할 url 경로
		setDefaultFailureUrl("/user/login/error?error=true&exception=" + errorMessage);
		
		super.onAuthenticationFailure(request, response, exception);
	}

}
