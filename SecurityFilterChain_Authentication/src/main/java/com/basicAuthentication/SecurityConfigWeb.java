package com.basicAuthentication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigWeb {
	
   @Bean
   public	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   
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
	   return http.build();
   }
	
   
   // 
   
   @Bean
   public AuthenticationManager authenticationManager( AuthenticationConfiguration authConfig) throws Exception {
	   
	   // yha hume btane ke jarurat nhi hoti hai ki hum kon sa Authenticaton use kar rahe hai
	   // In memory ya Database .
	   // by default database authentication hota hai
	   // ye automatically service ke pass chala jata hai
	   
	   // AuthenticationConfiguration ka object banenga phri hum uske ek method ko call karenge: getAuthenticationManager()
	   return authConfig.getAuthenticationManager();
   }
   
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
