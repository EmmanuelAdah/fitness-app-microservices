package com.server.userservice.data.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users",
    indexes = {
            @Index(name = "idx_user_id", columnList = "id"),
            @Index(name = "idx_user_email", columnList = "email")
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
    private String role;
}
