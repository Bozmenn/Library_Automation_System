package com.berkozmen.library_automation_system.model.entity;

import com.berkozmen.library_automation_system.model.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "User Api model documentation", description = "Model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of User object")
    private Long id;

    @Size(min = 5, max = 25, message = "username length should be between 5 and 25 characters")
    @Column(unique = true, nullable = false)
    @ApiModelProperty(value = "User name field of User object")
    private String username;

    @Column(unique = true, nullable = false)
    @ApiModelProperty(value = "Email field of User object")
    private String email;

    @Size(min = 5, message = "Minimum password length: 5 characters")
    @ApiModelProperty(value = "Password field of User object")
    private String password;

    @OneToMany(cascade = CascadeType.MERGE)
    @ApiModelProperty(value = "Book requests field of User object")
    private List<BookRequest> bookRequests;
    @ApiModelProperty(value = "Book reservations field of User object")
    @OneToMany(cascade = CascadeType.MERGE)
    private List<BookReservation> bookReservations;
    @OneToMany(cascade = CascadeType.MERGE)
    @ApiModelProperty(value = "Book feedbacks field of User object")
    private List<BookFeedback> bookFeedbacks;
    @ElementCollection(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "Roles field of User object")
    private List<Role> roles;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
