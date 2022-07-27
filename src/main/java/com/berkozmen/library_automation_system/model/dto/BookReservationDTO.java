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

    private User user;
    private Book book;
    private Date startDate;
    private Date endDatePlanned;
    private Date endDateActual;

}
