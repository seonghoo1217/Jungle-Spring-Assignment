package swjungle.week13.assignment.global.dto;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProvider {
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long access_expiration;

    @Value("${jwt.refresh.expiration}")
    private Long refresh_expiration;

    @Value("${jwt.access.header}")
    private String access_header;

    @Value("${jwt.refresh.header}")
    private String refresh_header;

    public void generateSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
