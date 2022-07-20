package com.berkozmen.library_automation_system.model.entity;

import com.berkozmen.library_automation_system.model.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Book> Books;



}
