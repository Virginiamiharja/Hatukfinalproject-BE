package com.backend.hatukfinalproject.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private	UserRepo userRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private EmailUtil emailUtil;

	private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	
	private String uploadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";

	@Override
	@Transactional
	public Iterable<User> getAllUsers() {
		return userRepo.findAllUser();
	}
	
	@Override
	@Transactional
	public Iterable<User> getAllTherapists() {
		return userRepo.findAllTherapist();
	}

	@Override
	public User userLogin(String username, String password) {
		// Kita check dulu dia itu username atau email
		boolean isEmail = username.contains("@");
		
		User findUser;
	
		if(isEmail == true) {
			findUser = userRepo.findByEmail(username).get();	
		} else {
			findUser = userRepo.findByUsername(username).get();
		}
			
		if(pwEncoder.matches(password, findUser.getPassword())) {
//			Untuk nampilin null di console log
			findUser.setPassword("");
			return findUser;
		}
		
		throw new RuntimeException("Wrong password");
	}
	
	@Override
	public User userKeepLogin(int userId) {
		User findUser = userRepo.findById(userId).get();
		
		if (findUser == null) {
			return null;
		}
		
		return findUser;
	}
	
	@Override
	public User userRegister(MultipartFile profilePicture, String registerForm, int cityId) throws JsonMappingException, JsonProcessingException {
		User user = new ObjectMapper().readValue(registerForm, User.class);
		City findCity = cityRepo.findById(cityId).get();
		String encodedPassword = pwEncoder.encode(user.getPassword());
//		Disini pake optional aja jangan pake get, krn kalo pake get itu dia kaya gamau jalanin pokoknya kl hasilnya null
		Optional<User> findUsername = userRepo.findByUsername(user.getUsername());
		Optional<User> findUserEmail = userRepo.findByEmail(user.getEmail());
		
		String fileExtension = profilePicture.getContentType().split("/")[1];
		String newFileName = "ProfilePicture-" + user.getUsername() + "." + fileExtension;
		String fileName = StringUtils.cleanPath(newFileName);
		Path path = Paths.get(StringUtils.cleanPath(uploadPath) + fileName);
		
		String verifyToken = pwEncoder.encode(user.getUsername() + user.getPassword());
		
		try {
			Files.copy(profilePicture.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String image = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/download/").path(fileName).toUriString();
		
//		Kalo isPresent dia ngereturn bool, tapi kl get dia ngeretur no value present
		if (findUsername.isPresent()) {
			throw new RuntimeException("Username exists!");
		} else if (findUserEmail.isPresent()) {
			throw new RuntimeException("Email exists!");
		}
		
		user.setVerified(false);
		user.setVerifyToken(verifyToken);
		user.setPassword(encodedPassword);
		user.setCity(findCity);
		user.setImage(image);
		
		// Kirim verifyToken si user ke emailnya user
		String linkToVerify = "http://localhost:8080/users/verify/" + user.getUsername() + "?token=" + verifyToken;
				
		String message = "<h1>Congrats! Registration Success</h1>\n";
		message += "Account with username " + user.getUsername() + " is registered!\n";
		message += "Click <a href=\"" + linkToVerify + "\">this link</a> to verify your account.";
		
		this.emailUtil.sendEmail(user.getEmail(), "Mom Story - New User Verification", message);
		
		return userRepo.save(user);
	}

	@Override
	public User resetPassword(int userId, User user) {
		User findUser = userRepo.findById(userId).get();
		String encodedPassword = pwEncoder.encode(user.getPassword());
	
		findUser.setPassword(encodedPassword);
		return userRepo.save(findUser);
		
	}

	@Override
	public User getUserById(int userId) {
		User findUser = userRepo.findById(userId).get();
		return findUser;
	}

	@Override
	public String verifyUserEmail(String username, String token) {
		User findUser = userRepo.findByUsername(username).get();
		
		if (findUser.getVerifyToken().equals(token)) {
			findUser.setVerified(true);
		} else {
			throw new RuntimeException("Token is invalid");
		}
		
		userRepo.save(findUser);
		return "Your account is verified";
	}

	@Override
	public String forgotPassword(String email) {
		User user = userRepo.findByEmail(email).get();
		
		String resetPassLink = "http://localhost:3000/resetpassword/" + user.getId();
//		String resetPassLink = "http://localhost:3000/resetpassword/" + user.getVerifyToken();
				
		String message = "<h1>Reset Password</h1>\n";
		message += "Hi " + user.getUsername() + " This is a link to reset your password!\n";
		message += "Click <a href=\"" + resetPassLink + "\"> this link</a> to reset your password.";
		
		this.emailUtil.sendEmail("virgnm@gmail.com", "Mom Story - Reset Password", message);
		
		return null;
	}

	@Override
	public User editUserProfile(int cityId, String currPass, User user) {
		User findUser = userRepo.findById(user.getId()).get();
		String encodedPassword = pwEncoder.encode(user.getPassword());
		City findCity = cityRepo.findById(cityId).get();
		
		// Ini validasi supaya sebelum user ganti password harus masukin password lama
		if (user.getPassword().equals(findUser.getPassword())) {
			user.setPassword(findUser.getPassword());
		} else {
			if(pwEncoder.matches(currPass, findUser.getPassword())) {
				user.setPassword(encodedPassword);
			} else {
				throw new RuntimeException("Password doesn't match");
			}
		}
		
//	Ini buat validasi biar walaupun di edit gaada username atau email yang sama
//	Optional<User> findUsername = userRepo.findByUsername(user.getUsername());
//	Optional<User> findUserEmail = userRepo.findByEmail(user.getEmail());

		user.setTransactions(findUser.getTransactions());
		user.setCity(findCity);
		return userRepo.save(user);
//		return null;
	}

	@Override
	public User editProfilePicture(int userId, MultipartFile profilePicture)
			throws JsonMappingException, JsonProcessingException {
		User findUser = userRepo.findById(userId).get();

//		Set penamaan dan location si picture
		String fileExtension = profilePicture.getContentType().split("/")[1];
		String newFileName = "ProfilePicture-" + findUser.getUsername() + "." + fileExtension;
		String fileName = StringUtils.cleanPath(newFileName);
		Path path = Paths.get(StringUtils.cleanPath(uploadPath) + fileName);
		
		try {
			Files.copy(profilePicture.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String image = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/download/").path(fileName).toUriString();
		findUser.setImage(image);
		
		return userRepo.save(findUser);
	}

	@Override
	public Iterable<Object[]> findTopSpendingUser() {
		return userRepo.findTopSpendingUser();
	}
	
//	@Override
//	public User editUserProfile(String currPass, MultipartFile profilePicture, String editUserForm) throws JsonMappingException, JsonProcessingException {
//		User user = new ObjectMapper().readValue(editUserForm, User.class);
//		User findUser = userRepo.findById(user.getId()).get();
//		String encodedPassword;
//		
////		Ini validasi supaya sebelum user ganti password harus masukin password lama
//		if (user.getPassword().length() > 0) {
//			System.out.println("Ada isi");
//			if(pwEncoder.matches(currPass, findUser.getPassword())) {
//				encodedPassword = pwEncoder.encode(user.getPassword());
//				user.setPassword(encodedPassword);
//				System.out.println("Bener nih bisa update");
//			} else {
//				throw new RuntimeException("Password doesn't match");
//			}
//			
//		} else {
//			user.setPassword(findUser.getPassword());
//			System.out.println("kosong");
//		}
//		
////		Ini buat validasi biar walaupun di edit gaada username atau email yang sama
////		Optional<User> findUsername = userRepo.findByUsername(user.getUsername());
////		Optional<User> findUserEmail = userRepo.findByEmail(user.getEmail());
////		
//		String fileExtension = profilePicture.getContentType().split("/")[1];
//		String newFileName = "ProfilePicture-" + user.getUsername() + "." + fileExtension;
//		String fileName = StringUtils.cleanPath(newFileName);
//		Path path = Paths.get(StringUtils.cleanPath(uploadPath) + fileName);
//		
//		try {
//			Files.copy(profilePicture.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		String image = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/download/").path(fileName).toUriString();
//		
//		user.setVerified(findUser.isVerified());
//		user.setVerifyToken(findUser.getVerifyToken());
//		user.setImage(findUser.getImage());
//		user.setCity(findUser.getCity());
//		
////		Kalo isPresent dia ngereturn bool, tapi kl get dia ngeretur no value present
////		if (findUsername.isPresent()) {
////			throw new RuntimeException("Username exists!");
////		} else if (findUserEmail.isPresent()) {
////			throw new RuntimeException("Email exists!");
////		}
//		
//		return userRepo.save(user);
////		return null;
//		
//	}

	

}
