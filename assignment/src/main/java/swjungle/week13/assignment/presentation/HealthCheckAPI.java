package swjungle.week13.assignment.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;

@RestController
public class HealthCheckAPI {

    @GetMapping("ping")
    public ResponseEnvelope<String> healthCheck() {
        return ResponseEnvelope.of("pong");
    }
}
