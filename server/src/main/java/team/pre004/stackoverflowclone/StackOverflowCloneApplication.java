package team.pre004.stackoverflowclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class StackOverflowCloneApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(StackOverflowCloneApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
