package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.helper.JwtUtil;
import com.security.model.JwtRequest;
import com.security.model.JwtResponse;
import com.security.serrvice.CustomeUserDetailsService;


@RestController
public class JwtController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private JwtUtil  jwtUtil;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println(jwtRequest);
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		} catch (Exception e) {
			e.printStackTrace();
			 throw new Exception("Bad credential");
		}
		
		
		UserDetails userDetails=this.customeUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	String token=this.jwtUtil.generateToken(userDetails);
	System.out.println(token);
	return ResponseEntity.ok(new JwtResponse(token));
	
	}
}
