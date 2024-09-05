package com.ng.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ng.springboot.security.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {

	public MyUser findByUser(String user);

}
