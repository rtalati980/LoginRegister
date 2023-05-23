package com.loginRegister.LoginRegister.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginRegister.LoginRegister.To.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);
}
