package com.parshuram.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.parshuram.security.credentialconfig.MyUserDetailsService;
import com.parshuram.security.utils.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private MyUserDetailsService service;

	@Autowired
	private JwtUtils jwtUtils;
	
	//getting token
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
		
		String username=null;
		String jwt=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			
			jwt = authorizationHeader.substring(7);
			
			username=jwtUtils.extractUsername(jwt);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		
			UserDetails userDetails = this.service.loadUserByUsername(username);
			
			if(jwtUtils.validateToken(jwt, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
							=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);				
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
