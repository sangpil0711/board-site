package kr.co.ymtech.bm.config;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * class : jdbctemplate 을 이용하여 DB와 연결을 수행하는 클래스
 * 
 * 작성일 : 2023.09.18
 * 작성자 : 박상현
 */
@Component
public class PostgreConnectionTest implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override 
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
           
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        jdbcTemplate.execute("");

        return;
    }
}