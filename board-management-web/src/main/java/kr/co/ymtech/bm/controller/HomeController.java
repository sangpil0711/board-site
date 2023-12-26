package kr.co.ymtech.bm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ymtech.bm.repository.IUserRepository;
import kr.co.ymtech.bm.repository.UserRepository;
import kr.co.ymtech.bm.repository.vo.UserVO;

@Controller
public class HomeController {

	@Autowired
	private final IUserRepository userRepository;

	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// 로그인 여부 확인
		if (auth == null || !UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
			model.setViewName("login"); // 로그인 안되어 있으면 로그인 페이지로 이동
		} else {

			try {

				if (auth.getAuthorities().isEmpty()) {
					model.setViewName("login");
				} else {
					model.setViewName("main");

					String username = auth.getName();
					UserVO userVO = userRepository.findByUsername(username);

					model.addObject("UserVO", userVO);

//        			Iterator<? extends GrantedAuthority> itrAuthority = auth.getAuthorities().iterator();
//        			GrantedAuthorityDetail authDetail = null;
//        			
//        			while (itrAuthority.hasNext()) {
//        				authDetail = (GrantedAuthorityDetail) itrAuthority.next();
//        				UserGrade userGrade = authDetail.getGrade();
//        				
//        				switch (userGrade.getId()) {
//        				case UserGrade.ADMIN:
//        				case UserGrade.USER:
//        					// 사용자 정보 가져오기
//        					String userJSON = new ObjectMapper().writeValueAsString(authDetail.getUser());
//        					JSONObject obj = new JSONObject(userJSON);
//        					
//        					break;
//        				case UserGrade.UNKNOWN_USER:
//        					
//        					break;
//        				case UserGrade.INVALID_UNKNOWN_AND_ERROR:
//        					
//        					break;
//        				case UserGrade.NOT_ENTERED_ID_OR_PASSWORD:
//        				default:
//        					
//        					break;
//        				}
//        			}
				}

			} catch (Exception e) {
			}
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
}
