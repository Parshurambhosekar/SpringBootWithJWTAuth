package com.parshuram.security.credentialconfig;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parshuram.security.exception.InvalidCredentialException;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username.equals("Parshuram")) {
			
			User user=new User("Parshuram", "$2a$12$gUFBV4uXlhU8oemf3ZiaGOnUMV6WP0hCmy58O6Y3IHJhOXp/sl1eG", new ArrayList<>());
			
			return user;
		}
		else {
			
			throw new InvalidCredentialException("Username is Incorrect....");
		}
	}

}
