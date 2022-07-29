package com.berkozmen.library_automation_system.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookFeedbacks")
public class BookFeedback {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id",referencedColumnName = "book_id")
    private Book book;
    private String feedback;


}
