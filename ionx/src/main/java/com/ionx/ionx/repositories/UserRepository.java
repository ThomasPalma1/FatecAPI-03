package com.ionx.ionx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ionx.ionx.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from usuario u where u.email = ?1")
	User findByEmail(String email);
	
}
