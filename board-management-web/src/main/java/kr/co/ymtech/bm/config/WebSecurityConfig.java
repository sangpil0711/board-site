package kr.co.ymtech.bm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	


	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// /about 요청에 대해서는 로그인을 요구함
				.antMatchers("/login").authenticated()
				// /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
				.antMatchers("/login").hasRole("ADMIN")
				// 나머지 요청에 대해서는 로그인을 요구하지 않음
				.anyRequest().permitAll().and()
				// 로그인하는 경우에 대해 설정함
				.formLogin()
				// 로그인 페이지를 제공하는 URL을 설정함
				.loginPage("/login")
				// 로그인 성공 URL을 설정함
				.successForwardUrl("/main")
				// 로그인 실패 URL을 설정함
				.failureForwardUrl("/login").permitAll();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/user/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
	}

	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

}
