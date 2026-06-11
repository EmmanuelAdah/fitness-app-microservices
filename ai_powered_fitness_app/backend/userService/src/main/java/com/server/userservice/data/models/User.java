package com.server.userservice.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User extends UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
