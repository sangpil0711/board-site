package kr.co.ymtech.bm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;

// Spring Boot에서 애플리케이션을 실행하는 중요한 역할
@SpringBootApplication
public class BoardManagementWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardManagementWebApplication.class, args);
	}
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}
}
