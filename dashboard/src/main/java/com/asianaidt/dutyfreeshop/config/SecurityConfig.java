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
	        .loginPage("/admin/members/login")
	        .defaultSuccessUrl("/admin/chart/chartForm")
	        .usernameParameter("id")
	        .failureUrl("/admin/members/login/error")
	        .and()
	        .logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/admin/members/logout"))
	        .logoutSuccessUrl("/admin/members/login");
	
		http.authorizeRequests()
	        .mvcMatchers("/**").permitAll()
	        .mvcMatchers("/admin/", "/admin/chart/**").hasRole("ADMIN")
	        .anyRequest().authenticated();
	
		http.exceptionHandling()
	        .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		return http.build();
    }
}
