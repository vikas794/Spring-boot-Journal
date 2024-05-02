package com.journalApp.Jorunal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/health-check/**").permitAll()
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.headers(headers -> {
                headers
                    .httpStrictTransportSecurity(Customizer.withDefaults())
                    .xssProtection(Customizer.withDefaults())
                    .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"));
            })
			;

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User
			.withUsername("user")
			.password(encoder().encode("user"))
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}

    @Bean
	public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}