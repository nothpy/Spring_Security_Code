package com.basicAuthentication;


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
	
   @Bean
   public UserDetailsService userDetailsService() {
	   InMemoryUserDetailsManager in = new InMemoryUserDetailsManager();
	   in.createUser(User
			   .withUsername("admin")
			   .password(bCryptPasswordEncoder().encode("admin1234"))
			   .roles("ADMIN")
			   .build()
			   );
	   in.createUser(User
			   .withUsername("member")
			   .password(bCryptPasswordEncoder().encode("member1234"))
			   .roles("MEMBER")
			   .build()
			   );
	   
	   return in;
   }
   
   
   @Bean
   public AuthenticationProvider authenticationProvider() {
	   DaoAuthenticationProvider daoAuthProvider =  new DaoAuthenticationProvider();
	   daoAuthProvider.setUserDetailsService(userDetailsService() );
	   daoAuthProvider.setPasswordEncoder(bCryptPasswordEncoder());
	   return daoAuthProvider ;
   }
   
   @Bean
   public AuthenticationManager authenticationManager( AuthenticationConfiguration authConfig) throws Exception {
	   
	   // AuthenticationManager by default databse vale method provider yani loadbyUsername ko chalayega lekin hum chahte hai ki vo InMemory use kare 
	   // uske liye hum khud ka AuthenticationProvider banate hai
	   // AuthenticationProvider --> hum is interface ka use karte hai khud ka provider banane ke liye 
	   // yadi hum AuthenticationProvider AND UserDetailsService nhi banate hai to ye default databse authentication use karega 
	   return authConfig.getAuthenticationManager();
   }
   
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
