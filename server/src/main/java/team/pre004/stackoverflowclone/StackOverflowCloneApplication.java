package team.pre004.stackoverflowclone;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team.pre004.stackoverflowclone.service.QuestionService;
import team.pre004.stackoverflowclone.service.impl.QuestionServiceImpl;

@EnableJpaAuditing
@SpringBootApplication
public class StackOverflowCloneApplication {


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(StackOverflowCloneApplication.class, args);


	}

}
