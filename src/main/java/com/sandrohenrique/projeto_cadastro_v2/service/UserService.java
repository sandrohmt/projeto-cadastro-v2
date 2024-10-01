package com.sandrohenrique.projeto_cadastro_v2.service;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.mapper.UserMapper;
import com.sandrohenrique.projeto_cadastro_v2.repository.UserRepository;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User save(UserPostRequestBody userPostRequestBody) {
        User user = userMapper.toUser(userPostRequestBody);
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
