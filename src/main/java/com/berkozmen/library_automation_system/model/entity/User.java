package com.berkozmen.library_automation_system.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "user_id")
    private Long id;

    @Size(min = 5, max = 25, message = "username length should be between 5 and 25 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 5, message = "Minimum password length: 5 characters")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<Role> roles;
/*    @OneToMany
    private List<BookRequest> bookRequests;*/
    @OneToMany
    private List<BookReservation> bookReservations;
/*    @OneToMany
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private List<BookFeedback> bookFeedbacks;*/

}
