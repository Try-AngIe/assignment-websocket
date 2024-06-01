package com.rlatkd.chat.member.controller;


import com.rlatkd.chat.member.dto.UserDto;
import com.rlatkd.chat.member.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class JoinController {

    @Autowired
    private JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody UserDto userDTO) {
        log.info("Username: {}", userDTO.getUsername());
        joinService.joinProcess(userDTO);
        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }
}
