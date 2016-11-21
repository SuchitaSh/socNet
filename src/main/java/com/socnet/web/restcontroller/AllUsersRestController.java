package com.socnet.web.restcontroller;

import java.util.Set;

import com.socnet.dto.BasicUserDto;
import com.socnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllUsersRestController {
    private UserService userService;

    @Autowired
    public AllUsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<BasicUserDto>> getCurrentUserFriends() {

        Set<BasicUserDto> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<Set<BasicUserDto>>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(allUsers);
    }
}
