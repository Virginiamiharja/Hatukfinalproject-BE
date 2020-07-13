package com.backend.hatukfinalproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.hatukfinalproject.dao.CityRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.City;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.UserService;
import com.backend.hatukfinalproject.util.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public User userLogin (@RequestParam String username, @RequestParam String password) {
		return userService.userLogin(username, password);
	}
	
	@GetMapping("/keeplogin")
	public User userKeepLogin (@RequestParam int userId) {
		return userService.userKeepLogin(userId);
	}
	
	@PostMapping("/register")
	public User userRegister(@RequestParam int cityId, @RequestParam ("registerForm") String registerForm, @RequestParam ("profilePicture") MultipartFile profilePicture) throws JsonMappingException, JsonProcessingException {
		return userService.userRegister(profilePicture, registerForm, cityId);
	}
	
	@GetMapping("/forgotpassword")
	public String forgotPassword(@RequestParam String email) {
		return userService.forgotPassword(email);
	}
	
	@PutMapping("/resetpassword")
	public User resetPassword(@RequestParam int userId, @RequestBody User user) {
		return userService.resetPassword(userId, user);
	}
	
	@GetMapping("/userprofile")
	public User getUserById(@RequestParam int userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/verify/{username}")
	public String verifyUserEmail (@PathVariable String username, @RequestParam String token) {
		return userService.verifyUserEmail(username, token);
	}
	
	@PutMapping("/edituserprofile")
	public User editUserProfile(@RequestParam int cityId, @RequestParam String currPass, @RequestBody User user) {
		return userService.editUserProfile(cityId, currPass, user);
	}
	
	@PutMapping("/editprofilepicture")
	public User editProfilePicture(@RequestParam int userId, @RequestParam ("profilePicture") MultipartFile profilePicture) throws JsonMappingException, JsonProcessingException {
		return userService.editProfilePicture(userId, profilePicture);
	}
	
//	@PutMapping("/edituserprofile")
//	public User editUserProfile(@RequestParam String currPass, @RequestParam ("editUserForm") String editUserForm, @RequestParam ("profilePicture") MultipartFile profilePicture) throws JsonMappingException, JsonProcessingException {
//		return userService.editUserProfile(currPass, profilePicture, editUserForm);
//	}
}
