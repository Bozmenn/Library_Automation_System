package com.berkozmen.library_automation_system.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class BookFeedback {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private User user;
    private String feedback;


}
