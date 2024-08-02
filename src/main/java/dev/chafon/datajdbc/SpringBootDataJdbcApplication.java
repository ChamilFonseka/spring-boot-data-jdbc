package dev.chafon.datajdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class SpringBootDataJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJdbcApplication.class, args);
	}

}
