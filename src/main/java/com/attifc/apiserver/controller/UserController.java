package com.attifc.apiserver.controller;

import com.attifc.apiserver.repository.MemoryUserRepository;
import com.attifc.apiserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.attifc.apiserver.domain.User;

import java.util.List;

// TODO : 결과에 따른 httpStatus 리턴 (지금은 항상 200ok 응답)

@RestController
public class UserController {
    MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    UserService userService = new UserService(memoryUserRepository);

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<User> getUsers() {
        return userService.findUsers();
    }

    @RequestMapping(value = "/api/join", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public User addUser(@RequestBody User user){
        userService.join(user);
        return user;
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public User updateUser(@RequestBody User user, @PathVariable int id){
        return userService.updateUser(id, user);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public User deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
