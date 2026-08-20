package io.github.hananjafari76.sample.user.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
public class UserResponse {
    private String userId;
    private String username;
    private String email;
    private Timestamp createdAt;

}
