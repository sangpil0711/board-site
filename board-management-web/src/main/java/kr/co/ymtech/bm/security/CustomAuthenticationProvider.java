package kr.co.ymtech.bm.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserVO;
import kr.co.ymtech.bm.security.model.UserGrade;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

   @Autowired
   private UserRepository userRepository;

   @Override
   public Authentication authenticate(Authentication authentication) {

      Object principal = authentication.getPrincipal();
      Object password = authentication.getCredentials();

      UserVO user = userRepository.findByUsername((String) principal);

      System.out.println(user);

//      // 명시적으로 타입 변환
//      Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);

      List<GrantedAuthority> list = new ArrayList<>();
      UserGrade grade = UserGrade.getUserGrade(0, "관리자", "관리자 권한");
      GrantedAuthorityDetail detail = new GrantedAuthorityDetail(grade);
      list.add(detail);

      // #1. id, password로 DB에서 조회한 결과로 비교 후 반환 분기
      if (user != null && user.getPassword().equals(password)) {
         return new UsernamePasswordAuthenticationToken(principal, password, list);
      } else {
         return new UsernamePasswordAuthenticationToken(principal, password, AuthorityUtils.NO_AUTHORITIES);
      }
      
      
      // UserDetailsService에서 사용자 정보를 가져옴
//        UserDetails userDetails = loadUserByUsername((String) principal);
//
//        if (userDetails != null && userDetails.getPassword().equals(password)) {
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            // 명시적으로 타입 변환
//            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities);
//            return new UsernamePasswordAuthenticationToken(principal, password, grantedAuthorities);
//        } else {
//            return null;
//        }
   }

   @Override
   public boolean supports(Class<?> authentication) {
      return authentication.equals(UsernamePasswordAuthenticationToken.class);
   }
}