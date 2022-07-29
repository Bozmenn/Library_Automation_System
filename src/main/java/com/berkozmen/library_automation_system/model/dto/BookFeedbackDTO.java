package com.berkozmen.library_automation_system.model.dto;

import com.berkozmen.library_automation_system.model.entity.User;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class BookFeedbackDTO {

    private Long user_id;
    private Long book_id;
    private String feedback;

}
