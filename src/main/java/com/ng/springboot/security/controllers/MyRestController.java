package com.ng.springboot.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ng.springboot.security.entity.MyUser;
import com.ng.springboot.security.repository.UserRepository;

@RestController
public class MyRestController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping(value = "/admin/")
	public ResponseEntity<String> adminUser() {

		// to retrive user id, not recommnded to print password/ user in prod
		MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println("My username is: " + user.getUser());
		System.out.println("My password is: " + user.getPassword());
		System.out.println("My Role is: " + user.getRole());
		System.out.println("My id is: " + user.getId());
		System.out.println("My org id is: " + user.getOrg());

		return ResponseEntity.ok("This is admin API");

	}

	@PostMapping(value = "/public/adduser")
	public ResponseEntity<String> addUser(@RequestBody MyUser user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);

		return ResponseEntity.accepted().build();

	}

	@GetMapping(value = "/public/")
	public ResponseEntity<String> publicUser() {

		return ResponseEntity.ok("This is public API");

	}

	@PreAuthorize("hasRole('ROLE_NORMAL')")
	@GetMapping(value = "/normal/")
	public ResponseEntity<String> normalUser() {

		return ResponseEntity.ok("This is normal API");

	}

	@GetMapping(value = "/api/")
	public ResponseEntity<String> api() {

		return ResponseEntity.ok("This is API User");

	}

}
