package io.github.hananjafari76.sample.user.controller;

import io.github.hananjafari76.sample.user.io.UserRequest;
import io.github.hananjafari76.sample.user.io.UserResponse;
import io.github.hananjafari76.sample.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody UserRequest request) {
        return userService.add(request);
    }

    @GetMapping
    public List<UserResponse> fetchUsers(){
        return userService.read();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String userId) {
        try {
            userService.delete(userId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



}
