package kr.co.ymtech.bm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.co.ymtech.bm.security.CustomAuthenticationFilter;
import kr.co.ymtech.bm.security.CustomAuthenticationProvider;
import kr.co.ymtech.bm.security.CustomLoginSuccessHandler;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
    private CustomAuthenticationProvider provider;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring();
	} // 정적 자원에 대한 보안설정 무시

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .httpBasic();
//        return http.build();
    	
		// HTTP 보안 구성을 정의합니다.
		http.headers(headers -> headers.frameOptions().sameOrigin()) // iframe에서의 동작을 설정합니다.
				.csrf(csrf -> csrf.disable()) // CSRF 보호를 비활성화합니다.
				.authorizeHttpRequests((authorize) -> authorize
						.antMatchers(
									 "/login",
									 "/boards/**",
									 "/static/**",
									 "/fileExplorer/**")
									 .permitAll() // 특정 경로에 대한 접근을 허용합니다.
									 .anyRequest().authenticated()) // 나머지 요청은 인증이 필요합니다.
				.formLogin(login -> login
						.loginPage("/login") // 로그인 페이지의 URL을 설정합니다.
//						.loginProcessingUrl("/j_security_check")
						.successForwardUrl("/") // 로그인 성공시 이동할 페이지를 설정합니다.
						.failureForwardUrl("/login") // 로그인 실패시 이동할 페이지를 설정합니다.
						.usernameParameter("username") // 사용자명 파라미터의 이름을 설정합니다.
						.passwordParameter("password")
						)
				.logout(logout -> logout
						.logoutUrl("/j_security_check_logout")
						.logoutSuccessUrl("/").invalidateHttpSession(false)).addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 비밀번호 파라미터의 이름을 설정합니다.

		return http.build();
    }
    
    @Bean
    CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }
    
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        customAuthenticationFilter.setFilterProcessesUrl("/j_security_check");
        customAuthenticationFilter.setAuthenticationManager(provider::authenticate);
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }
    
//    @Bean
//    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    
//	@Bean
//	WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring();
//	} // 정적 자원에 대한 보안설정 무시
//
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		// HTTP 보안 구성을 정의합니다.
//		http.headers(headers -> headers.frameOptions().sameOrigin()) // iframe에서의 동작을 설정합니다.
//				.csrf(csrf -> csrf.disable()) // CSRF 보호를 비활성화합니다.
//				.authorizeHttpRequests((authorize) -> authorize
//						.antMatchers("/",
//									 "/login",
//									 "/boards/**",
//									 "/static/**",
//									 "/fileExplorer/**")
//									 .permitAll() // 특정 경로에 대한 접근을 허용합니다.
//									 .anyRequest().authenticated()) // 나머지 요청은 인증이 필요합니다.
//				.formLogin(login -> login.loginPage("/login") // 로그인 페이지의 URL을 설정합니다.
//						.successForwardUrl("/main") // 로그인 성공시 이동할 페이지를 설정합니다.
//						.failureForwardUrl("/login") // 로그인 실패시 이동할 페이지를 설정합니다.
//						.permitAll() // 로그인 페이지는 모두에게 허용합니다.
//						.usernameParameter("username") // 사용자명 파라미터의 이름을 설정합니다.
//						.passwordParameter("password")); // 비밀번호 파라미터의 이름을 설정합니다.
//
//		return http.build();
//	}

}