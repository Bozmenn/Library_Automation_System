package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

   @Autowired
   private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> allBooks = bookRepository.findAll();
        return allBooks;
    }

    public Book getById(Long id){
       Optional<Book> byId = bookRepository.findById(id);
       return byId.orElseThrow(()->new RuntimeException());
    }


    public Book create(BookDTO bookDTO){
        Book book = BookMapper.toEntity(bookDTO);
        Book save = bookRepository.save(book);
        return save;

    }

    public void delete(Long id){
        getById(id);
        bookRepository.deleteById(id);
    }

    /*public void update(){
        bookRepository.
    }*/




}
