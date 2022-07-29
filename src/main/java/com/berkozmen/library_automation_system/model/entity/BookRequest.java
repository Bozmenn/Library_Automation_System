package com.berkozmen.library_automation_system.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_requests")
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    private String RequestedBookTitle;
    private String RequestedBookAuthor;
    @Enumerated(EnumType.STRING)
    private Status status;




}
