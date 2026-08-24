package io.github.hananjafari76.sample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Email
    private String email;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;


}
