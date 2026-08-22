package io.github.hananjafari76.sample.user.service;

import io.github.hananjafari76.sample.user.io.UserRequest;
import io.github.hananjafari76.sample.user.io.UserResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface UserService {
    List<UserResponse> read();

    UserResponse add(@RequestBody UserRequest request);

    void delete(String userId);

    UserResponse update(String userId, @Valid UserRequest request);
}
