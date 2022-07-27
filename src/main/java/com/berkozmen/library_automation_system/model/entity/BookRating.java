package com.berkozmen.library_automation_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BookRating {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int rating;

}
