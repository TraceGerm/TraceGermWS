package com.tracegerm.tracegermws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracegerm.tracegermws.model.user.User;

public interface IUserRepository extends JpaRepository<User, String>{
	

}
