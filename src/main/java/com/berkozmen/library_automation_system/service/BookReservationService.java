package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.repository.BookReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BookReservationService {

    @Autowired
    private BookReservationRepository bookReservationRepository;

    public List<BookReservation> getAllBookReservations(){
        List<BookReservation> all = bookReservationRepository.findAll();
        return all;
    }


    public BookReservation getById(Long id){
        Optional<BookReservation> byId = bookReservationRepository.findById(id);
        return byId.orElseThrow(()->new EntityNotFoundException("Book reservation"));
    }


/*    public BookReservation create(BookDTO bookDTO){
        Book book = BookMapper.toEntity(bookDTO);
        Book save = bookRepository.save(book);
        return save;

    }

    public void delete(Long id){
        getById(id);
        bookRepository.deleteById(id);
    }

    public Book update(String title, BookDTO bookDTO){
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if(!bookByTitle.isPresent()){
            throw new EntityNotFoundException("Book");
        }
        Book updatedBook = bookByTitle.get();
        if(!StringUtils.isEmpty(bookDTO.getTitle())){
            updatedBook.setTitle(bookDTO.getTitle());
        }if(!StringUtils.isEmpty(bookDTO.getAuthor())){
            updatedBook.setAuthor(bookDTO.getAuthor());
        }if(!StringUtils.isEmpty(bookDTO.getISBN())){
            updatedBook.setISBN(bookDTO.getISBN());
        }if(!StringUtils.isEmpty(bookDTO.getPublisher())){
            updatedBook.setPublisher(bookDTO.getPublisher());
        }if(!StringUtils.isEmpty(bookDTO.getPublishedDate())){
            updatedBook.setPublishedDate(bookDTO.getPublishedDate());
        }
        return bookRepository.save(updatedBook);

    }*/



}
