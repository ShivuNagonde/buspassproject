package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.model.User;
import com.spring.service.UserServiceImpl;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/user")
public class UserController {

	String path = "E:\\myprojets\\spring-boot-bus-pass-with-mysql-app\\tessdata\\";
	String input = "C:\\Users\\Shivakumar\\Pictures\\";
	@Autowired
	private UserServiceImpl userServiceImpl;

	// localhost:8080/user/register
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public Object userRegistration(@RequestParam("Adhar Card") MultipartFile file1,
			@RequestParam("User Photo") MultipartFile file2, User user) throws IOException {
		String encodstring1 = encodeFileToBase64Binary(file1.getBytes());
		String encodstring2 = encodeFileToBase64Binary(file2.getBytes());

		ITesseract tesseract = new Tesseract();
		tesseract.setDatapath(path);
		String text1 = null;

		try {
			text1 = tesseract.doOCR(new File(input + file1.getOriginalFilename()));
		} catch (TesseractException e) {
			System.out.println("Exception : " + e.getMessage());
		}
		user.setUserAdharCard(encodstring1);
		user.setUserPhoto(encodstring2);
		user.setUserText1(text1);

		return userServiceImpl.userRegister(user);
	}

	// localhost:8080/user/getAllUsers
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUsers() {
		return userServiceImpl.getAllUsers();
	}

	// localhost:8080/user/getUserByUserRegId/{id}
	@RequestMapping(value = "/getUserByUserRegId/{id}", method = RequestMethod.GET, produces = "application/json")
	public Object getUserByUserRegId(@PathVariable("id") int id) {
		return userServiceImpl.getUserByUserRegId(id);
	}

	// localhost:8080/user/updateUser
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = "application/json")
	public Object updateUser(@RequestBody User user) {
		return userServiceImpl.updateUser(user);
	}

	// localhost:8080/user/deleteUser/{id}
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Object deleteUserByUserRegId(@PathVariable("id") int id) {
		return userServiceImpl.deleteUserByUserRegId(id);

	}

	// localhost:8080/user/deleteAllUsers
	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.DELETE, produces = "application/json")
	public String deleteAllUsers() {
		userServiceImpl.deleteAllUsers();
		return "All Users Successfully Deleted.";
	}

	// @SuppressWarnings("resource")
	private String encodeFileToBase64Binary(byte[] bytes) {
		String encodedFile = null;
		try {
			encodedFile = Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedFile;
	}
}
