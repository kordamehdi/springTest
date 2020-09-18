package com.example.demo.config.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.config.filter.consants.SecurityConstants;
import com.example.demo.entities.security.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	
	public JwtAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
		//Gestionnaire gestionnaire = null;
		AppUser user = null;
		try {
		//	gestionnaire = new ObjectMapper().readValue(request.getInputStream(), Gestionnaire.class);
			user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("********");
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()) );
	}
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse
			response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
//		super.successfulAuthentication(request, response, chain, authResult);
		User springUser=(User)authResult.getPrincipal();
		String jwtToken=Jwts.builder()
		.setSubject(springUser.getUsername())
		.setExpiration(new
		Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
		.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
		.claim("roles", springUser.getAuthorities())
		.compact();
		response.addHeader(SecurityConstants.HEADER_STRING,
		SecurityConstants.TOKEN_PREFIX+jwtToken);
	}

}
