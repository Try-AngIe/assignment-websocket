package com.rlatkd.chat;

import com.rlatkd.chat.member.dto.UserDto;
import com.rlatkd.chat.member.repository.UserRepository;
import com.rlatkd.chat.member.service.JoinService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataLoader {

    private final JoinService joinService;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.deleteAll();
        UserDto testUser = new UserDto();
        testUser.setUsername("test");
        testUser.setPassword("1004");
        joinService.joinProcess(testUser);
    }

}
