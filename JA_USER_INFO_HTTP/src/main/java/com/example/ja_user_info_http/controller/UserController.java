package com.example.ja_user_info_http.controller;

import com.example.ja_user_info_http.model.UserResponse;
import com.example.ja_user_info_http.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userDetail")
    public ResponseEntity<UserResponse> getUserDetail(@RequestParam("user") String username) {
        UserResponse response = userService.getUserDetail(username);
        if (response != null) {
            userService.postUserDetail(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
