package com.axelote.demo.config;

import com.axelote.adapter.configuration.AxeloteAdapter;
import com.axelote.adapter.configuration.Cors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AxeloteAdapter
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${credentials.username}")
    private String username;

    @Value("${credentials.password}")
    private String password;

    @Value("${cors.origin}")
    private String origin;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOriginPatterns(origin)
                        .allowedMethods(Cors.ALL_METHODS)
                        .allowedHeaders(Cors.ALL_HEADERS);
            }
        };
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user =
                users.username(username)
                        .password(password)
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/authenticate").permitAll()
                        .requestMatchers("/api/**").hasRole("USER")
                ).httpBasic();

        return http.build();
    }
}
