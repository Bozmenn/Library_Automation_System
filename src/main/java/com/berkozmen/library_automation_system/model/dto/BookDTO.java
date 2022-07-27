package com.berkozmen.library_automation_system.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {

    private String title;
    private String author;
    private long ISBN;
    private String publisher;
    private Date publishedDate;
    private int rating;

}
