package swjungle.week13.assignment.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import swjungle.week13.assignment.global.application.CustomUserDetailService;
import swjungle.week13.assignment.global.application.JwtUtil;
import swjungle.week13.assignment.global.application.dto.ReissueToken;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailService customUserDetailService;

    private final JwtUtil jwtUtil;

    private final ObjectMapper objectMapper;

    private static final List<String> AUTHENTICATE_WHITELIST = List.of(
            "/members/signup", "/members/signin", "/ping"
    );

    private static final List<String> AUTHENTICATE_GUEST = List.of(
            "/articles", "/articles/**"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("REQUEST-URI:" + request.getRequestURI());
        if (AUTHENTICATE_WHITELIST.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().equals("/articles") && request.getMethod().equals("GET")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");
        String authorizationRefresh = request.getHeader("Authorization_Refresh");
        if (authorizationRefresh != null) {
            ReissueToken reissueToken = jwtUtil.reissue(authorizationRefresh);
            response.setHeader("Authorization", reissueToken.accessToken());
            response.setHeader("Authorization_Refresh", reissueToken.refreshToken());
        }
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String accessToken = eliminatePrefixBearer(authorization);

            if (!isAuthorizationCorrect(accessToken)) {
                tokenInvalidException(response);
                return;
            }

            if (jwtUtil.isValidateToken(accessToken)) {
                String username = jwtUtil.getClaimUsername(accessToken);

                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    private String eliminatePrefixBearer(String authorizationToken) {
        return authorizationToken.substring(7);
    }

    private boolean isAuthorizationCorrect(String accessToken) {
        return accessToken != null && !accessToken.trim().isEmpty();
    }

    private void tokenInvalidException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseEnvelope<Void> responseEnvelope = ResponseEnvelope.of(
                "400",
                null,
                "토큰이 유효하지 않습니다."
        );

        objectMapper.writeValue(response.getOutputStream(), responseEnvelope);
    }
}
