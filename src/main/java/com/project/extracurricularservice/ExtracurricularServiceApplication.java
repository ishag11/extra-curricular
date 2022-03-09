package com.project.extracurricularservice;

import com.project.extracurricularservice.entity.SpringSecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ExtracurricularServiceApplication {
	@Bean
	public AuditorAware<String> auditorAware(){

		return new SpringSecurityAuditorAware();
	}
	public static void main(String[] args) {
		SpringApplication.run(ExtracurricularServiceApplication.class, args);
	}

}




