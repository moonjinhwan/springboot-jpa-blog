package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().disable()
			.authorizeRequests()
				.antMatchers("/auth/**", "/css/**", "/image/**", "/js/**", "/")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/auth/loginForm");		
	}
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
}
