package com.attifc.apiserver.repository;

import com.attifc.apiserver.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    User deleteById(Long id);
    List<User> findAll();
    void clear();
}
