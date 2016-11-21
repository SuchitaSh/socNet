package com.socnet.web.controller;

import com.socnet.persistence.entities.User;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showRootPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        User user = userService.getCurrentUser();

        model.addAttribute(user);
        model.addAttribute("currentUser", userService.getCurrentUser());

        return "home";
    }
    
    @GetMapping("/home/{username}")
    public String showUserHomePage(@PathVariable String username, Model model){
        User someone = userService.findUserByUsername(username);

        model.addAttribute("user", someone);
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (userService.isCurrentUserFollowerOf(userService.findUserByUsername(username))) {
            model.addAttribute("follower", true);
        }

        if (userService.isCurrentUserFriendOf(someone)) {
            model.addAttribute("friend", true);
        }

    	return "user-page";
    }

    @GetMapping("/settings")
    public String showSettingsPage(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "user-settings";
    }

    @PostMapping("/change-information")
    public String chageInformation(Model model, @RequestParam(value = "photo", required = false) MultipartFile file,
                                   HttpServletRequest request, @RequestParam(value = "name", required = false) String userName,
                                   @RequestParam(value = "surname", required = false) String surname) {
        User user = userService.getCurrentUser();
        if (userName!=null || !userName.equals("")) user.setFirstName(userName);
        if (surname!=null || !surname.equals("")) user.setLastName(surname);
        userService.save(user);
        String name = null;
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        if (file!=null) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    name = file.getOriginalFilename();
                    File uploadedFile = new File(rootDirectory + "resources\\usersImages\\" + user.getId() + ".png");
                    file.transferTo(uploadedFile);
                    System.out.println("uploaded: " + uploadedFile.getAbsolutePath());

                    System.out.println("You successfully uploaded file=" + name);

                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            }
        }
            return "redirect:/home";
    }
}
