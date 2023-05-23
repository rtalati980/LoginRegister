package com.loginRegister.LoginRegister.Config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.loginRegister.LoginRegister.Repository.UserRepository;
import com.loginRegister.LoginRegister.To.Users;

public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user=userRepo.findByEmail(username);
		if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Not found: " + username);
        }}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

}
