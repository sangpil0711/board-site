package kr.co.ymtech.bm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	
	
	 
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
       return (web) -> web.ignoring();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.headers(headers -> headers.frameOptions().sameOrigin())
                .csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize.antMatchers("/" //
                 , "/signup" //
                 , "/login" //
                 , "/tveta/libs/**" //
                 , "/boards/**" //
                 , "/static/**"
                 , "/fileExplorer/**") //
                		
                .permitAll()
                // 앞에서 설정한 이외의 모든 페이지는 로그인 필요
                .anyRequest().authenticated())
                .formLogin(login -> login
                        // 로그인 페이지 URL은 "/login"
                        .loginPage("/login")
                        // 로그인 성공시 "/"로 이동
                        .successForwardUrl("/")
                        // 로그인 실패시 "/login"로 이동
                        .failureForwardUrl("/login").permitAll()
                        .usernameParameter("userId")//
                        .passwordParameter("userPw"));
        
        return http.build();
    }

}
