package io.github.hananjafari76.sample.user.service.impl;

import io.github.hananjafari76.sample.user.io.UserRequest;
import io.github.hananjafari76.sample.user.io.UserResponse;
import io.github.hananjafari76.sample.user.model.UserEntity;
import io.github.hananjafari76.sample.user.repository.UserRepository;
import io.github.hananjafari76.sample.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse add(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);

        return convertToResponse(newUser);
    }



    @Override
    public List<UserResponse> read(){
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String userId) {
        UserEntity existingUser = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found : " + userId));
        userRepository.delete(existingUser);
    }

    @Override
    @Transactional
    public UserResponse update(String userId, UserRequest request) {
        UserEntity existingUser = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found : " + userId));

        existingUser.setUsername(request.getUsername());

        if(request.getPassword() != null && !request.getPassword().isEmpty()) {
            existingUser.setPassword(request.getPassword());
        }
        existingUser.setEmail(request.getEmail());

        UserEntity updatedUser = userRepository.save(existingUser);
        return convertToResponse(updatedUser);
    }

    private UserEntity convertToEntity(UserRequest request) {
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(request.getPassword() != null ? request.getPassword() : "defaultPassword")
                .email(request.getEmail())
                .build();
    }

    private UserResponse convertToResponse(UserEntity newUser) {
        return UserResponse.builder()
                .userId(newUser.getUserId())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .createdAt(newUser.getCreatedAt())
                .build();
    }
}
