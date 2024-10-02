package com.sandrohenrique.projeto_cadastro_v2.service;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.exception.BadRequestException;
import com.sandrohenrique.projeto_cadastro_v2.mapper.UserMapper;
import com.sandrohenrique.projeto_cadastro_v2.repository.UserRepository;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findByIdOrThrowBadRequestException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Question not found!"));
    }

    public User save(UserPostRequestBody userPostRequestBody) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getEmail(), userPostRequestBody.getEmail())) {
                throw new BadRequestException("The provided email is already in use!");
            }
        }
        User user = userMapper.toUser(userPostRequestBody);
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void delete(Long id) {
        userRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}

// Nomear certo os outros métodos do repository