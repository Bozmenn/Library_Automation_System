package com.berkozmen.library_automation_system.model.dto;

import com.berkozmen.library_automation_system.model.entity.Status;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BookRequestDTO {

    private Long user_id;
    private String RequestedBookTitle;
    private String RequestedBookAuthor;
    @Enumerated(EnumType.STRING)
    private Status status;

}
