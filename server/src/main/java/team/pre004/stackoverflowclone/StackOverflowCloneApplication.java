package team.pre004.stackoverflowclone;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import team.pre004.stackoverflowclone.service.QuestionService;
import team.pre004.stackoverflowclone.service.impl.QuestionServiceImpl;

@EnableJpaAuditing
@SpringBootApplication
public class StackOverflowCloneApplication {



	public static void main(String[] args) {
		SpringApplication.run(StackOverflowCloneApplication.class, args);


	}

}
