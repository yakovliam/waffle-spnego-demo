package com.example.security;

import com.example.WaffleProvider;
import com.example.inspection.HeaderRemovalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import waffle.spring.NegotiateSecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final WaffleProvider waffleProvider = new WaffleProvider();


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth.requestMatchers("/protected").authenticated()
            .requestMatchers("/static/**", "/favicon.ico").permitAll()  // Allow static resources
            .anyRequest().permitAll()).addFilterBefore(waffleProvider.getNegotiateSecurityFilter(),
            BasicAuthenticationFilter.class)
        .addFilterBefore(new HeaderRemovalFilter(), NegotiateSecurityFilter.class)
        .httpBasic(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(ex -> ex.authenticationEntryPoint(
            waffleProvider.getNegotiateSecurityFilterEntryPoint()));
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/static/**", "/favicon.ico", "/error");
  }
}