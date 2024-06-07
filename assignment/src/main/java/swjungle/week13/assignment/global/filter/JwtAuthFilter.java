package swjungle.week13.assignment.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import swjungle.week13.assignment.global.application.CustomUserDetailService;
import swjungle.week13.assignment.global.application.JwtUtil;
import swjungle.week13.assignment.global.application.dto.ReissueToken;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailService customUserDetailService;

    private final JwtUtil jwtUtil;

    private static final List<String> AUTHENTICATE_WHITELIST = List.of(
            "/members/signup", "/members/signin", "/ping"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("REQUEST-URI:" + request.getRequestURI());
        if (AUTHENTICATE_WHITELIST.contains(request.getRequestURI())) {
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
}
