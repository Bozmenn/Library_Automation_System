package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Optional;
@Slf4j
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
       return byId.orElseThrow(()->{
           log.error("Related Book cannot find by id");
           return new EntityNotFoundException("Book");
       });
    }

    public Book getByTitle(String title){
        Optional<Book> byId = bookRepository.findBookByTitle(title);
        return byId.orElseThrow(()->new EntityNotFoundException("Book"));
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

    public Book update(Long id, BookDTO bookDTO){
        getById(id);
        Book updatedBook = BookMapper.toEntity(bookDTO);
        return bookRepository.save(updatedBook);
    }




}
