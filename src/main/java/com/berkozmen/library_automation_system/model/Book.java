package com.berkozmen.library_automation_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private int bookId;
    private String title;
    private String author;
    private int rating;
    private long ISBN;
    private String publisher;
    private String publishedDate;


}
