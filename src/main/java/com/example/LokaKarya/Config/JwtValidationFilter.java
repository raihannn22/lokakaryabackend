//package com.example.LokaKarya.Config;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import employees_post_test_day2.employees_day2.Dto.ManagerDto;
//import employees_post_test_day2.employees_day2.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtValidationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
//            throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            doFilter(request, response, filterChain);
//            return;
//        }
//
//        try {
//            final String jwt = authHeader.substring(7);
//            final String userEmail = jwtUtil.extractUsername(jwt);
//
//            if (userEmail != null
//                    && SecurityContextHolder.getContext().getAuthentication() == null) {
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//                if (jwtUtil.isTokenValid(jwt)) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(userEmail, null, authorities);
//                    authToken
//                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                } else {
//                    throw new RuntimeException("invalid token!");
//                }
//            }
//
//            filterChain.doFilter(request, response);
//
//        } catch (Exception e) {
//            response.setStatus(HttpStatus.FORBIDDEN.value());
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//            e.printStackTrace();
//            objectMapper.writeValue(response.getOutputStream(), "NOT OKAY");
//        }
//    }
//}
