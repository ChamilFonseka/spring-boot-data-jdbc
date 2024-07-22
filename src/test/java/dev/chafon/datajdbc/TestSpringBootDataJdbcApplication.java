package dev.chafon.datajdbc;

import org.springframework.boot.SpringApplication;

public class TestSpringBootDataJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootDataJdbcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
