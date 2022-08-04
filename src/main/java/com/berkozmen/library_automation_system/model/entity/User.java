package com.berkozmen.library_automation_system.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 25, message = "username length should be between 5 and 25 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 5, message = "Minimum password length: 5 characters")
    private String password;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<BookRequest> bookRequests;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<BookReservation> bookReservations;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<BookFeedback> bookFeedbacks;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
