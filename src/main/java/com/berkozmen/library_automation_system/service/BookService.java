package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.model.Book;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

   @Autowired
   private BookRepository bookRepository;


    public List<Book> getAllBooks(){
        List<Book> allBooks = bookRepository.findAll();
        return allBooks;
    }

    public Book create(BookDTO bookDTO){
        Book book = BookMapper.toEntity(bookDTO);
        Book book1 = bookRepository.create(book);
        return book1;
    }
}
