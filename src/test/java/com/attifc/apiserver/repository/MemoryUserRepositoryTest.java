package com.attifc.apiserver.repository;

import com.attifc.apiserver.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryUserRepositoryTest {

    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {
        User user = new User();
        user.setEmail("aaa@aaa.com");
        user.setName("soo");
        user.setPassword("1234");

        repository.save(user);
        User result = repository.findById(user.getId()).get();
        Assertions.assertThat(user).isEqualTo(result);
    }

    @Test
    public void findByName() {
        User user = new User();
        user.setEmail("aaa@aaa.com");
        user.setName("soo");
        user.setPassword("1234");
        repository.save(user);

        User result = repository.findByName("soo").get();
        Assertions.assertThat(user).isEqualTo(result);
    }

    @Test
    public void findByEmail() {
        User user = new User();
        user.setEmail("aaa@aaa.com");
        user.setName("soo");
        user.setPassword("1234");
        repository.save(user);

        User result = repository.findByEmail("aaa@aaa.com").get();
        Assertions.assertThat(user).isEqualTo(result);
    }

    @Test
    public void findAll() {
        User user = new User();
        user.setEmail("aaa@aaa.com");
        user.setName("soo");
        user.setPassword("1234");
        repository.save(user);

        User user2 = new User();
        user2.setEmail("bbb@bbb.com");
        user2.setName("soo2");
        user2.setPassword("1234");
        repository.save(user2);

        List<User> userList = repository.findAll();

        Assertions.assertThat(userList.size()).isEqualTo(2);


    }
}
