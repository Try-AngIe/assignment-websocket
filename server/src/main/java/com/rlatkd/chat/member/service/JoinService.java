package com.rlatkd.chat.member.service;

import com.rlatkd.chat.member.dto.UserDto;
import com.rlatkd.chat.member.entity.UserEntity;
import com.rlatkd.chat.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(UserDto userDto) {

        //db에 이미 동일한 username을 가진 회원이 존재하는지?
        boolean isUser = userRepository.existsByUsername(userDto.getUsername());
        if (isUser) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(userDto.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
