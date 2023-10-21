package com.payroll.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService service() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security)throws Exception{
		return security
				.userDetailsService(this.service())
				.csrf(csrf -> csrf.disable())
        		.authorizeHttpRequests((auth)->{
        			auth
        			.requestMatchers("/admin/**").hasRole("ADMIN")
        			.requestMatchers("/employee/**").hasRole("EMPLOYEE")
        			.requestMatchers("/**").permitAll()
        			.anyRequest()
        			.authenticated();
        		})
        		.formLogin(form->{
        			form
        			.loginPage("/auth/signin")
        			.defaultSuccessUrl("/auth/validateLoginUser", true);
        		})
				.build();
				
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web)-> web.ignoring().requestMatchers("/contents/**","/img/**");
	}
}
