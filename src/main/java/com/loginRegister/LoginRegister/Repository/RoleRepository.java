package com.loginRegister.LoginRegister.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginRegister.LoginRegister.To.Role;
import com.loginRegister.LoginRegister.To.Users;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	List<Role> findByName (String name);

}
