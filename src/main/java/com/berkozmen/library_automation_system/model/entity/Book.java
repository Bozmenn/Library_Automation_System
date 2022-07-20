package com.berkozmen.library_automation_system.model.entity;

import com.berkozmen.library_automation_system.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int rating;
    private long ISBN;
    private String publisher;
    private String publishedDate;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    private User user;




}
