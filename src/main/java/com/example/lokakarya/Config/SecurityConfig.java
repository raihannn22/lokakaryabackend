package com.example.lokakarya.Config;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration

public class SecurityConfig {

    @Autowired
    private final JwtValidationFilter jwtValidationFilter;

    @Bean
    public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionmangement -> sessionmangement
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .cors(cors -> cors.configurationSource(request -> {
                            CorsConfiguration cfg = new CorsConfiguration();
                            cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
                            cfg.setAllowedMethods(Collections.singletonList("*"));
                            cfg.setAllowedMethods(Arrays.asList("GET", "PATCH" ,"POST", "PUT", "DELETE", "OPTIONS"));
                            cfg.setAllowCredentials(true);
                            cfg.setAllowedHeaders(Collections.singletonList("*"));
                            cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                            cfg.setExposedHeaders(Arrays.asList("Authorization"));
                            return cfg;
                        }))
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(
                            auth -> auth.requestMatchers("/users/**").hasAuthority("HR")
                                    .anyRequest().permitAll())
                    .csrf(csrf -> csrf.ignoringRequestMatchers("/**")
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }

        
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}