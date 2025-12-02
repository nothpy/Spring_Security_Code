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
	   .csrf().disable()
       .authorizeHttpRequests()
           .antMatchers("/admin").authenticated()
           .antMatchers("/admin/**").authenticated() // yha /admin to authenticate ho lekin /admin / se suru hone vale sabhi url bhi authenticated hone chaiye
           .anyRequest().permitAll()
       .and()
           .formLogin()
           .loginPage("/login")
           .loginProcessingUrl("/doLogin") // is url se username and password aa raha hai form se ab spring security ko pta chal jayega ki isi url se data process karna hai
           .defaultSuccessUrl("/admin/xyz") // login hone ke baad kha jaye ye hume batata hai after login 
       .and()
           .logout()
           .logoutUrl("/doLogout")
           
           
           ;
	   
	   
	   return http.build();
   }
	

}
