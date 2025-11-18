package com.basicAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.basicAuthentication.service.MyService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfigWeb extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyService myService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.authorizeHttpRequests()
//		.antMatchers("/member").authenticated()  // "/member" and "/admin " vali request per id password fill karne honge kyoki ye authenticated ho gayi hai
//		.antMatchers("/admin").authenticated()   
//		.anyRequest().permitAll()                 // inke alav koi bhi request aayegi vo authenticated nhi hongi 
//		.and()
//		.httpBasic();
	
		http
		.authorizeHttpRequests()
		.antMatchers("/member").hasRole("MEMBER")
		.antMatchers("/admin").hasRole("ADMIN")
		.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/error/403");
		
	}
	
	// role based Authentication 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.inMemoryAuthentication()
//		.withUser("admin123")
//		.password(bCryptPasswordEncoder().encode("admin333"))
//		.roles("ADMIN")
//		.and()
//		.withUser("member123")
//		.password(bCryptPasswordEncoder().encode("member333"))
//		.roles("MEMBER");
		
		// service ke kis method me database se data read karne ka code likha hai. uske liye hum ek standardization use karte hai 
		// MyService ko hum implements karte hai UserDetailsService se ye ek Interface hai
		auth.userDetailsService(myService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
