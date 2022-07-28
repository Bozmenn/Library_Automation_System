package com.berkozmen.library_automation_system.model.mapper;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookRequestDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookRequestMapper {

    @Autowired
    private UserService userService;

    public BookRequestDTO toDTO(BookRequest bookRequest){
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setUser_id(bookRequest.getUser().getId());
        bookRequestDTO.setRequestedBookTitle(bookRequest.getRequestedBookTitle());
        bookRequestDTO.setRequestedBookAuthor(bookRequest.getRequestedBookAuthor());
        return bookRequestDTO;
    }

    public BookRequest toEntity(BookRequestDTO bookRequestDTO){
        BookRequest bookRequest = new BookRequest();
        bookRequest.setUser(userService.getById(bookRequestDTO.getUser_id()));
        bookRequest.setRequestedBookTitle(bookRequestDTO.getRequestedBookTitle());
        bookRequest.setRequestedBookAuthor(bookRequestDTO.getRequestedBookAuthor());

        return bookRequest;
    }
}
