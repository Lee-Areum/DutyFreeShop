package com.asianaidt.dutyfreeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http.formLogin()
	        .loginPage("/members/login")
	        .defaultSuccessUrl("/")
	        .usernameParameter("id")
	        .failureUrl("/members/login/error")
	        .and()
	        .logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
	        .logoutSuccessUrl("/");
	
		http.authorizeRequests()
	        .mvcMatchers("/members/**", "/", "/exchangeRate/**", "/changeLocale/**", "/product/productList/**", "/product/productOne/**", "/searchproduct/**").permitAll()
	        .mvcMatchers("/admin/**").hasRole("ADMIN")
	        .anyRequest().authenticated();
	
		http.exceptionHandling()
	        .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();
    }
}
