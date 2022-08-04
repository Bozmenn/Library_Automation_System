package com.berkozmen.library_automation_system.model.dto;

import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReservationDTO {

    private Long id;
    private Long user_id;
    private Long book_id;
    private Date startDate;
    private String endDatePlanned;
    private Date endDateActual;


}
