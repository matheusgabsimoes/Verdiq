package com.verdiq.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(Long id, String name, String email, String password) {
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Name, email, and password cannot be null");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
