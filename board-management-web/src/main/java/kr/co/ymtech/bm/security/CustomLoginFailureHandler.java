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

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errorMessage;
		if (exception instanceof BadCredentialsException) {
			errorMessage = "비밀번호가 일치하지 않습니다. 다시 확인해 주세요.";
		} else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
		} else if (exception instanceof AuthenticationServiceException) {
			errorMessage = "인증 과정 중 문제가 발생했습니다. 관리자에게 문의하세요.";
		} else {
			errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
		}
		
		request.getSession().setAttribute("username", request.getParameter("username"));
		
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		setDefaultFailureUrl("/login/error?error=true&exception=" + errorMessage);
		
		super.onAuthenticationFailure(request, response, exception);
	}

}
