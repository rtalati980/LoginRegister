package com.loginRegister.LoginRegister.Controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loginRegister.LoginRegister.Config.CustomUserDetailsService;
import com.loginRegister.LoginRegister.Repository.RoleRepository;
import com.loginRegister.LoginRegister.Repository.UserRepository;
import com.loginRegister.LoginRegister.To.Role;
import com.loginRegister.LoginRegister.To.Users;

import jakarta.validation.groups.Default;


@Controller
public class UserController {
	
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Autowired
	public UserRepository uRepository;
	
	@Autowired
	public RoleRepository repository;
	
	
	  @GetMapping("/login") 
	  public String loginString() { 
	    return "login";
	  
	  }
	 
	
	@GetMapping("/")
	public String indexString() {
		return "index";
		
	}
	
	 @GetMapping("/admin") 
	  public String adminString() { 
	    return "admin";
	  }
	
	@GetMapping("/register")
    public String showNewUsersForm(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "register";
    }
	@PostMapping("/add")
    public String saveUsers(@ModelAttribute("user") Users user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setRoles(repository.findByName("USERS"));
		uRepository.save(user);
        return "redirect:/login";
    }
	
	@GetMapping("/register/admin")
    public String showADMINUsersForm(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "registeradmin";
    }
	
	@PostMapping("/add/admin")
    public String saveADMINUsers(@ModelAttribute("user") Users user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setRoles(repository.findByName("ADMIN"));
		uRepository.save(user);
        return "redirect:/login";
    }
	
	/*
	 * @PostMapping("/login") public String login(@RequestParam("username") String
	 * username, @RequestParam("password") String password, CustomUserDetailsService
	 * cUserDetailsService) { username
	 * =cUserDetailsService.loadUserByUsername(username).getUsername();
	 * password=cUserDetailsService.loadUserByUsername(username).getPassword();
	 * return "index"; }
	 */
}
