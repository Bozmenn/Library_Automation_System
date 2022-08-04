package com.berkozmen.library_automation_system.model.mapper;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.service.BookService;
import com.berkozmen.library_automation_system.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BookReservationMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public BookReservationDTO toDTO(BookReservation bookReservation){
        BookReservationDTO bookReservationDTO = new BookReservationDTO();
        bookReservationDTO.setId(bookReservation.getId());
        bookReservationDTO.setUser_id(bookReservation.getUser().getId());
        bookReservationDTO.setBook_id(bookReservation.getBook().getId());
        bookReservationDTO.setStartDate(bookReservation.getStartDate());
        bookReservationDTO.setEndDatePlanned(bookReservation.getEndDatePlanned());
        bookReservationDTO.setEndDateActual(bookReservation.getEndDateActual());
        return bookReservationDTO;
    }

    public BookReservation toEntity(BookReservationDTO bookReservationDTO){
        BookReservation bookReservation = new BookReservation();
        bookReservation.setId(bookReservationDTO.getId());
        bookReservation.setUser(userService.getById(bookReservationDTO.getUser_id()));
        bookReservation.setBook(bookService.getById(bookReservationDTO.getBook_id()));
        bookReservation.setStartDate(bookReservationDTO.getStartDate());
        bookReservation.setEndDatePlanned(bookReservationDTO.getEndDatePlanned());
        bookReservation.setEndDateActual(bookReservationDTO.getEndDateActual());
        return bookReservation;
    }
}
