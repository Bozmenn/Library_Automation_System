package com.berkozmen.library_automation_system.model.dto;

import com.berkozmen.library_automation_system.model.entity.User;
import lombok.Data;

@Data
public class BookRequestDTO {

    private Long user_id;
    private String RequestedBookTitle;
    private String RequestedBookAuthor;

}
