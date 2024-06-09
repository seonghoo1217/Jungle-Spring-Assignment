package swjungle.week13.assignment;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import swjungle.week13.assignment.global.application.SecretKeyGenerator;

@SpringBootApplication
@RequiredArgsConstructor
public class AssignmentApplication {

    private final SecretKeyGenerator secretKeyGenerator;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

}
