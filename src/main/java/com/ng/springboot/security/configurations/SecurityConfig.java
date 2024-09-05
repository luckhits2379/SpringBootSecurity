package com.ng.springboot.security.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ng.springboot.security.service.MyUserDetailService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean//@formatter:off
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
					.csrf().disable()
						.authorizeHttpRequests()
							.requestMatchers("/public/**").permitAll()
							.requestMatchers("/admin/**").hasRole("ADMIN")
							.requestMatchers("/api/**").hasRole("API")
							.anyRequest().authenticated()
					   .and()
					        .formLogin()//.loginPage("/login").permitAll()
					   .and()
					        .httpBasic();

		
		return httpSecurity.build();

	}

	@Bean //@formatter:off
	UserDetailsService myUserDetailsService() {

		//UserDetails adminUser = User.withUsername("admin").password(myPasswordEncoder().encode("password")).roles("ADMIN").build();

		//UserDetails normalUser = User.withUsername("normal").password(myPasswordEncoder().encode("password")).roles("NORMAL").build();
		
		//UserDetails apiUser = User.withUsername("api").password(myPasswordEncoder().encode("password")).roles("API").build();

		//InMemoryUserDetailsManager userService = new InMemoryUserDetailsManager(adminUser, normalUser, apiUser);

		return new MyUserDetailService();

	}

	@Bean
	PasswordEncoder myPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
