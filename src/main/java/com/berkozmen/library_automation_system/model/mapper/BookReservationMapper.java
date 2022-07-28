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
        bookReservationDTO.setUser_id(bookReservation.getUser().getId());
        bookReservationDTO.setBook_id(bookReservation.getBook().getId());
        bookReservationDTO.setEndDatePlanned(bookReservation.getEndDatePlanned());
        return bookReservationDTO;
    }

    public BookReservation toEntity(BookReservationDTO bookReservationDTO){
        BookReservation bookReservation = new BookReservation();
        bookReservation.setUser(userService.getById(bookReservationDTO.getUser_id()));
        bookReservation.setBook(bookService.getById(bookReservationDTO.getBook_id()));
        bookReservation.setEndDatePlanned(bookReservationDTO.getEndDatePlanned());
        return bookReservation;
    }
}
