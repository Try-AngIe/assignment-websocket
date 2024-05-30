package com.rlatkd.chat.member.controller;

import com.rlatkd.chat.member.dto.CustomUserDetails;
import com.rlatkd.chat.member.dto.UserDTO;
import com.rlatkd.chat.member.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/test")
    public void test(@AuthenticationPrincipal CustomUserDetails userDetails) {
        System.out.println("tes: " + userDetails.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginP(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        try {
            request.login(userDTO.getUsername(), userDTO.getPassword());
        } catch (ServletException e) {
            return ResponseEntity.status(401).body("Login failed");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userDTO.getUsername());
    }
}
