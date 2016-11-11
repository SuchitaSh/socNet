package com.socnet.web.controller;

import com.socnet.persistance.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
	private static final String KEY_REGISTER_ERROR = "register-error";
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String doRegister(@RequestParam String username,@RequestParam String password,
							 @RequestParam String email, @RequestParam String name, 
			                 @RequestParam String surname,
			                 Model model){

		if(! userService.isUsernameAvailable(username)){
			model.addAttribute(KEY_REGISTER_ERROR, true);
			return "redirect:/login";
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(name);
		user.setLastName(surname);
		user.setEmail(email);
	

		
		//todo : add from string to date conversation

		userService.save(user);
		
		return "redirect:/login";
	}
}
