package com.attifc.apiserver.service;

import com.attifc.apiserver.domain.User;
import com.attifc.apiserver.repository.MemoryUserRepository;
import com.attifc.apiserver.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long join(User user) {
        isDuplicatedUser(user); // 중복회원 검증
        User result = userRepository.save(user);
        return result.getId();
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(long id) {
        return userRepository.findById(id);
    }

    public User updateUser(long id, User user) {
        Optional<User> result = userRepository.findById(id);

        if(result.isEmpty())
            return null;

        user.setId(id);

        return userRepository.update(user);
    }

    public User deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    private void isDuplicatedUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원 " + user.getEmail());
                });
    }
}
