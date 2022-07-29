package com.berkozmen.library_automation_system.model.mapper;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookFeedbackDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookFeedback;
import com.berkozmen.library_automation_system.service.BookService;
import com.berkozmen.library_automation_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookFeedbackMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public BookFeedbackDTO toDTO(BookFeedback bookFeedback){
        BookFeedbackDTO bookFeedbackDTO = new BookFeedbackDTO();
        bookFeedbackDTO.setUser_id(bookFeedback.getUser().getId());
        bookFeedbackDTO.setBook_id(bookFeedback.getBook().getId());
        bookFeedbackDTO.setFeedback(bookFeedback.getFeedback());
        return bookFeedbackDTO;
    }

    public BookFeedback toEntity(BookFeedbackDTO bookFeedbackDTO){
        BookFeedback bookFeedback = new BookFeedback();
        bookFeedback.setUser(userService.getById(bookFeedbackDTO.getUser_id()));
        bookFeedback.setBook(bookService.getById(bookFeedbackDTO.getBook_id()));
        bookFeedback.setFeedback(bookFeedbackDTO.getFeedback());
        return bookFeedback;
    }
}
