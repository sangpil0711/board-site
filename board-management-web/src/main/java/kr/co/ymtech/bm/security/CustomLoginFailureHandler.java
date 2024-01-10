package kr.co.ymtech.bm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 *  로그인 CustomLoginFailureHandler 클래스 
 */
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/**
	 * Method : 로그인 실패시 오류메세지를 나타나는 함수
	 * 
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 *
	 * @author 황상필
	 * @since 2024. 1. 5.
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 예외 메시지 가져오기
	    String errorMessage = exception.getMessage();

	    // 로그인 실패 시 입력했던 아이디
	    request.getSession().setAttribute("username", request.getParameter("username"));
	    request.getSession().setAttribute("loginError", errorMessage);
	    
	    response.sendRedirect("/");
	}

}
