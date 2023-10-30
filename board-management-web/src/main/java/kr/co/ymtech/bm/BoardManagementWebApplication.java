package kr.co.ymtech.bm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

// Spring Boot에서 애플리케이션을 실행하는 중요한 역할
@SpringBootApplication
public class BoardManagementWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardManagementWebApplication.class, args);
	}

//	@Bean
//	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
//		return new HiddenHttpMethodFilter();
//	}


}
