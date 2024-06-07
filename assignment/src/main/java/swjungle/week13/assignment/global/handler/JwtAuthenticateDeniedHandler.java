package swjungle.week13.assignment.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticateDeniedHandler implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseEnvelope<Void> responseEnvelope = ResponseEnvelope.of(
                "400",
                null,
                "토큰이 유효하지 않습니다."
        );

        objectMapper.writeValue(response.getOutputStream(), responseEnvelope);
    }
}
