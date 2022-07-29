package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookFeedbackDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookFeedback;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.mapper.BookFeedbackMapper;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.model.mapper.BookRequestMapper;
import com.berkozmen.library_automation_system.repository.BookFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookFeedbackService {

    @Autowired
    private BookFeedbackRepository bookFeedbackRepository;

    @Autowired
    private BookFeedbackMapper bookFeedbackMapper;

    public List<BookFeedback> getAllBookFeedbacks(){
        List<BookFeedback> allBookFeedbacks = bookFeedbackRepository.findAll();
        return allBookFeedbacks;
    }

    public BookFeedback getById(Long id){
        Optional<BookFeedback> byId = bookFeedbackRepository.findById(id);
        return byId.orElseThrow(()->new EntityNotFoundException("Book feedback"));
    }

    public BookFeedback getByUserId(Long user_id){
        Optional<BookFeedback> bookFeedbackByUserId = bookFeedbackRepository.findBookFeedbackByUserId(user_id);
        return bookFeedbackByUserId.orElseThrow(()->new EntityNotFoundException("Book feedback"));
    }

    public BookFeedback getByBookId(Long book_id){
        Optional<BookFeedback> bookFeedbackByBookId = bookFeedbackRepository.findBookFeedbackByBookId(book_id);
        return bookFeedbackByBookId.orElseThrow(()->new EntityNotFoundException("Book feedback"));
    }


    public BookFeedback create(BookFeedbackDTO bookFeedbackDTO){
        BookFeedback bookFeedback = bookFeedbackMapper.toEntity(bookFeedbackDTO);
        BookFeedback save = bookFeedbackRepository.save(bookFeedback);
        return save;
    }

    public void delete(Long id){
        getById(id);
        bookFeedbackRepository.deleteById(id);
    }

}
