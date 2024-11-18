//    package com.example.LokaKarya.Config;
//
//    import java.util.Arrays;
//    import java.util.Collections;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.http.HttpMethod;
//    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//    import org.springframework.security.config.http.SessionCreationPolicy;
//    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//    import org.springframework.security.crypto.password.PasswordEncoder;
//    import org.springframework.security.web.SecurityFilterChain;
//    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//    import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//    import org.springframework.web.cors.CorsConfiguration;
//    import lombok.RequiredArgsConstructor;
//    //import ogya.training.homework.config.filter.JwtValidationFilter;
//
//    @RequiredArgsConstructor
//    @EnableWebSecurity
//    @Configuration
//    public class SecurityConfig {
//
//        private final JwtValidationFilter jwtValidationFilter;
//
//        @Bean
//        public SecurityFilterChain mySecurityConfig(HttpSecurity http) throws Exception {
//
//            // CORS configuration
//            http.sessionManagement(sessionmangement -> sessionmangement
//                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .cors(cors -> cors.configurationSource(request -> {
//                            CorsConfiguration cfg = new CorsConfiguration();
//                            cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
//                            cfg.setAllowedMethods(Collections.singletonList("*"));
//                            cfg.setAllowedMethods(Arrays.asList("GET", "PATCH" ,"POST", "PUT", "DELETE", "OPTIONS"));
//                            cfg.setAllowCredentials(true);
//                            cfg.setAllowedHeaders(Collections.singletonList("*"));
//                            cfg.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//                            cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                            return cfg;
//                        }))
//
//                    .csrf(csrf -> csrf.disable())
//
//    //                .authorizeHttpRequests(auth -> auth
//    //                        .requestMatchers(HttpMethod.POST, "/employee/create").permitAll() // Ijinkan akses ke /employee/create tanpa autentikasi
//    //                        .anyRequest().authenticated());
//
//                    .authorizeHttpRequests(
//                        auth ->    auth.requestMatchers(HttpMethod.POST, "/employee/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/department/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/job/**").permitAll()
//                                .requestMatchers("/employee/get/count").permitAll()
//                                    .requestMatchers("/employee/**").authenticated()
//                                    .requestMatchers("/department/**").authenticated()
//                                    .requestMatchers("/job/**").authenticated()
//                                    .requestMatchers("/job-history/**").authenticated().anyRequest()
//                                    .permitAll())
////                            auth -> auth.requestMatchers("/employee/get/all").authenticated().requestMatchers("/employees/get/**").authenticated().anyRequest()
////                                    .permitAll())
//                    .csrf(csrf -> csrf.ignoringRequestMatchers("/**")
//                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                    .addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class);
//
//            return http.build();
//        }
//
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//    }