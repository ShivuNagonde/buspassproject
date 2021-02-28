package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.been.JwtRequest;
import com.spring.configuration.JwtTokenUtil;
import com.spring.model.Role;
import com.spring.model.User;
import com.spring.repository.RoleRepository;
import com.spring.repository.UserRepository;
import com.spring.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	// login via userEmail & userPassword
	// localhost:8080/login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object createLoginToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		Map<String, Object> map = new HashMap<>();
		authenticate(authenticationRequest.getUserEmail(), authenticationRequest.getUserPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		User u = userRepo.findByUserEmail(authenticationRequest.getUserEmail());
		Role r = roleRepo.findByRoleStatus(u.getUserRoleStatus());
		if (u != null) {
			map.put("token", token);
			map.put("username", u.getUserName());
			map.put("password", u.getUserPassword());
			map.put("email", u.getUserEmail());
			map.put("Well Come", r.getRoleName() + " Login Successfully.");
		}
		return map;
	}

	private void authenticate(String userEmail, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}