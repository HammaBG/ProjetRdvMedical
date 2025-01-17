package com.app.rdv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

        PasswordEncoder passwordEncoder= passwordEncoder();

        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("54321")).authorities("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("qwerty")).authorities("USER", "ADMIN").build()
        );
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/api/medecin/add", "/api/rdv/add", "/api/patient/add").hasAuthority("ADMIN")
                        .requestMatchers("/api/medecin/all").permitAll()
                )
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
