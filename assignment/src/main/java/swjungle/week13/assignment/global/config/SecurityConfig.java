package swjungle.week13.assignment.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import swjungle.week13.assignment.global.application.CustomUserDetailService;
import swjungle.week13.assignment.global.application.JwtUtil;
import swjungle.week13.assignment.global.filter.JwtAuthFilter;
import swjungle.week13.assignment.global.handler.JwtAccessDeniedHandler;
import swjungle.week13.assignment.global.handler.JwtAuthenticateDeniedHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticateDeniedHandler jwtAuthenticateDeniedHandler;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;
    private final ObjectMapper objectMapper;

    private static final String[] AUTHENTICATE_WHITELIST = {
            "/members/signup", "members/signin", "/ping"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers(AUTHENTICATE_WHITELIST).permitAll()
                    .requestMatchers(HttpMethod.GET, "/articles").permitAll()
                    .anyRequest().authenticated();
        });
        http.addFilterBefore(new JwtAuthFilter(customUserDetailService, jwtUtil, objectMapper), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(exceptionHandle -> {
            exceptionHandle.accessDeniedHandler(jwtAccessDeniedHandler);
            exceptionHandle.authenticationEntryPoint(jwtAuthenticateDeniedHandler);
        });
        return http.build();
    }
}
