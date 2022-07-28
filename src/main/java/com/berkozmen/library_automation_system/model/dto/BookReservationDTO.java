package com.berkozmen.library_automation_system.model.dto;

import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.User;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Data
public class BookReservationDTO {

    private Long user_id;
    private Long book_id;
    private String endDatePlanned;


}
