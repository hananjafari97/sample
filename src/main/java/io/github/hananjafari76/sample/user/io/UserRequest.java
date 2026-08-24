package io.github.hananjafari76.sample.user.io;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "User is required")
    private String username;
    @NotNull
    @Size(min = 4)
    private  String password;
    @NotBlank
    @Email
    private  String email;
}
