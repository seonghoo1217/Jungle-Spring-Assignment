package swjungle.week13.assignment.global.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableConfigurationProperties(WebConfigProperties.class)
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final WebConfigProperties webConfigProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(webConfigProperties.cors().allowedOrigins().toArray(String[]::new))
                .allowedMethods(webConfigProperties.cors().allowedMethods().toArray(String[]::new))
                .allowedHeaders(webConfigProperties.cors().allowedHeaders().toArray(String[]::new))
                .allowCredentials(webConfigProperties.cors().allowCredentials())
                .maxAge(3600);
    }
}
