package com.rlatkd.chat.member.service;


import com.rlatkd.chat.member.dto.UserDto;
import com.rlatkd.chat.member.entity.UserEntity;
import com.rlatkd.chat.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto authenticateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername()); // 사용자가 없으면 null 반환
        if (userEntity == null || !bCryptPasswordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            return null;
        }
        return userDto;
    }

}
