package kr.co.ymtech.bm.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import kr.co.ymtech.bm.repository.ChartRepository;
import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserVO;
import kr.co.ymtech.bm.security.model.UserGrade;

/**
 * 로그인 CustomAuthenticationProvider 클래스
 */
@EnableScheduling
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ChartRepository chartRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Scheduled(cron = "0 0 0 * * *")
	public void clearSession() {
		chartRepository.deleteVisitData();
	}

	/**
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 *
	 * @author 박상현
	 * @since 2024. 1. 5.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {

		// 사용자가 입력한 아이디와 비밀번호 가져옴
		Object principal = authentication.getPrincipal();
		Object password = authentication.getCredentials();

		UserVO user = userRepository.findByUsername((String) principal);

		// 사용자가 존재하지 않는 경우
		if (user == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
		} else {
			// 사용자 권한 정보를 생성
			List<GrantedAuthority> list = new ArrayList<>();
			UserGrade grade = UserGrade.getUserGrade(user.getGradeId(), user.getName(), user.getDescription());
			GrantedAuthorityDetail detail = new GrantedAuthorityDetail(grade);
			list.add(detail);

			// 입력된 password와 DB에서 가져온 password가 일치하는 경우
			if (passwordEncoder.matches((String) password, user.getPassword())) {
				chartRepository.boardVisitor(new Date().getTime(), (String) principal);
				return new UsernamePasswordAuthenticationToken(principal, password, list);
			} else {
				// 비밀번호가 일치하지 않는 경우
				throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
			}
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}