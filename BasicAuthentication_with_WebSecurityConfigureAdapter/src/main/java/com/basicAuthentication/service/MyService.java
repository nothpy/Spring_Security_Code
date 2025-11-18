package com.basicAuthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.basicAuthentication.UserDetailsImpl;
import com.basicAuthentication.dao.UserRepo;
import com.basicAuthentication.entity.User;

@Service
public class MyService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	public UserDetails readData(String username) {
		Optional<User> opt = userRepo.findByUsername(username);
		if(opt.isPresent()) {
			User user = opt.get();
			System.out.println(user);
			return new UserDetailsImpl(user);
		}else {
			throw new UsernameNotFoundException("-----------------User Not Found------------------");
		}
	}

  // UserDetailsService ka ek method hai  loadUserByUsername . 
	// ab spring security confuse nhi hoga vo direct isko search karega
	// ab chaye hum kitne bhi method bna le spring security confuse nhi hoga 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		Optional<User> opt = userRepo.findByUsername(username);
		if(opt.isPresent()) {
			User user = opt.get();
			System.out.println(user);
			return new UserDetailsImpl(user);
		}else {
			throw new UsernameNotFoundException("-----------------User Not Found------------------");
		}
	}
	
}
