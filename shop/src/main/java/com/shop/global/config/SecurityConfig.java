package com.shop.global.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.shop.global.common.security.handler.CustomAccessDeniedHandler;
import com.shop.global.common.security.handler.CustomAuthenticationFailureHandler;
import com.shop.global.common.security.handler.CustomAuthenticationSuccessHandler;
import com.shop.global.common.security.service.PrincipalDetailsService;
import com.shop.global.common.security.service.PrincipalOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	final private PrincipalOAuth2UserService customOAuth2UserService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailsService())
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage("/account/login")
		.loginProcessingUrl("/account/login")
		.successHandler(authenticationSuccessHandler())
		.failureHandler(authenticationFailureHandler());
		
		http.oauth2Login()
		.defaultSuccessUrl("/")
		.userInfoEndpoint()
		.userService(customOAuth2UserService);
		
		http.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler());
		
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");
		
		http.rememberMe()
		.key("remember-me")
		.tokenRepository(createJDBCRepository())
		.tokenValiditySeconds(60*60*24);

	}
	
	@Bean
	public UserDetailsService principalDetailsService() {
		return new PrincipalDetailsService();
	}
	
	/*
	 * @Bean public OAuth2UserService<OAuth2UserRequest, OAuth2User>
	 * customOAuth2UserService() { return new CustomOAuth2UserService(); }
	 */
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}
	
}