package kr.co.ymtech.bm.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *  로그인 CustomLoginSuccessHandler 클래스 
 */
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	/**
	 * Method : 로그인 성공시 메인화면으로 이동하는 함수
	 * 
	 * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 *
	 * @author 박상현
	 * @since 2024. 1. 5.
	 */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
    	// 인증이 성공하면 SecurityContextHolder에 현재 인증 정보를 설정합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 로그인 성공 후에는 / 경로로 리다이렉트합니다
        response.sendRedirect("/");
    }

}
