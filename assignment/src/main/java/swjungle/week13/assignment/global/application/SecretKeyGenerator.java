package swjungle.week13.assignment.global.application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import swjungle.week13.assignment.global.dto.JwtProvider;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SecretKeyGenerator implements ApplicationRunner {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
    private final JwtProvider jwtProvider;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String newSecretKey = generateRandomSecretKey();
        jwtProvider.generateSecretKey(newSecretKey);
        System.out.println("New secret key generated: " + newSecretKey);
    }

    @Scheduled(cron = "0 0 0 */3 * ?")
    public void generateScheduledSecretKey() {
        String newSecretKey = generateRandomSecretKey();
        jwtProvider.generateSecretKey(newSecretKey);
        System.out.println("New secret key generated: " + newSecretKey);
    }

    private String generateRandomSecretKey() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length() - 10)));
        }

        for (int i = 0; i < 4; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(10) + 52));
        }

        for (int i = 0; i < 2; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(10, 52)));
        }

        return sb.toString();
    }
}
