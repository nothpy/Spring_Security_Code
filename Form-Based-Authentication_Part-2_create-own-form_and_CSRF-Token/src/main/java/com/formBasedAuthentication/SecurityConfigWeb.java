package com.formBasedAuthentication;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigWeb {
	
   @Bean
   public	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   
	   http
//	   .csrf().disable()
       .authorizeHttpRequests()
           .antMatchers("/admin").hasRole("ADMIN")
           .antMatchers("/member").hasRole("MEMBER")
           .anyRequest().permitAll()
       .and()
           .formLogin()
           .loginPage("/login")
           .loginProcessingUrl("/doLogin") 
           .defaultSuccessUrl("/admin")
       .and()
           .logout()
           .logoutUrl("/doLogout")

           ;
	   
	   
	   return http.build();
   }
   
   @Bean
   public AuthenticationManager authenticationManager( AuthenticationConfiguration authConfig) throws Exception {
	  
	   return authConfig.getAuthenticationManager();
   }
   
   @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
