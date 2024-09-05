package com.ng.springboot.security.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class MyUser implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(unique = true)
	private String user;
	private String password;
	private String role;
	@Transient
	int org = 1300;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrg() {
		return org;
	}

	public void setOrg(int org) {
		this.org = org;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {

		this.role = "ROLE_" + role.toUpperCase();
	}

	public void setPassword(String password) {

		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role);

		return List.of(auth);
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return user;
	}

}
