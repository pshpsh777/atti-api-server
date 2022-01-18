package com.attifc.apiserver.service;

import com.attifc.apiserver.domain.User;
import com.attifc.apiserver.repository.MemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    MemoryUserRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryUserRepository();
        userService = new UserService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    void join() {
        // given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        // when
        long newId = userService.join(user);

        // then
        User getUser = userService.findUser(newId).get();
        Assertions.assertThat(user).isEqualTo(getUser);
    }

    @Test
    void duplicatedJoin() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        //when
        long newId1 = userService.join(user);

        //then
        assertThrows(IllegalStateException.class, ()->userService.join(user));
    }

    @Test
    void findUsers() {
    }

    @Test
    void updateUser() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        User user1 = new User();
        user1.setName("soo");
        user1.setPassword("5555");
        user1.setEmail("smile@gate.com");

        //when
        long newId1 = userService.join(user);
        User updatedUser = userService.updateUser(newId1, user1);

        //then
        Assertions.assertThat(updatedUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    void deleteUser() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        //when
        long newId1 = userService.join(user);
        User deletedUser = userService.deleteUser(newId1);
        List<User> users = userService.findUsers();

        //then
        Assertions.assertThat(deletedUser.getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(users.size()).isEqualTo(0);

    }
}