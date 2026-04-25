package uz.silkStep.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@EnableJpaAuditing
@SpringBootApplication
@EnableRedisRepositories
public class SilkStepApplication {
	public static void main(String[] args) {
		SpringApplication.run(SilkStepApplication.class, args);
	}
}

// This is the main application class for the SilkStep project. 
//It is annotated with @SpringBootApplication, which indicates that it is a Spring Boot application.