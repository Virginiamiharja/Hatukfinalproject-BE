package com.backend.hatukfinalproject.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.backend.hatukfinalproject.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface UserService {
	
	public Iterable <User> getAllUsers();
	
	public User userLogin (String username, String password);
	
	public User userKeepLogin (int userId);
	
	public User userRegister (MultipartFile profilePicture, String registerForm, int cityId) throws JsonMappingException, JsonProcessingException;
	
	public String forgotPassword(String email);
	
	public User resetPassword(int userId, User user);
	
	public User getUserById(int userId);
	
	public String verifyUserEmail(String username, String token);
	
	public User editUserProfile (int cityId, String currPass, User user);
	
	public User editProfilePicture(int userId, MultipartFile profilePicture) throws JsonMappingException, JsonProcessingException;
	
//	public User editUserProfile (String currPass, MultipartFile profilePicture, String editUserForm) throws JsonMappingException, JsonProcessingException;

	
}
