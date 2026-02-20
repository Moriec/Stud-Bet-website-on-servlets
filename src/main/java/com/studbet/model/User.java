package com.studbet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "balance")
    private int balance;
    @Column(name = "rating_points")
    private int ratingPoints;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    @Column(name = "last_login")
    private LocalDateTime lastLoginDate;

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = 1000;
    }
}