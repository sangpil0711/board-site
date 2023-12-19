package kr.co.ymtech.bm.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
    	// 인증이 성공하면 SecurityContextHolder에 현재 인증 정보를 설정합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 로그인 성공 후에는 / 경로로 리다이렉트합니다
        response.sendRedirect("/");
    }

}
