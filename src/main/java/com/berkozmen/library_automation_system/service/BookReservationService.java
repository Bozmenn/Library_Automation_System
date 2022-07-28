package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.model.mapper.BookReservationMapper;
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
    @Autowired
    private BookReservationMapper bookReservationMapper;

    @Autowired
    private BookService bookService;


    public List<BookReservation> getAllBookReservations(){
        List<BookReservation> all = bookReservationRepository.findAll();
        return all;
    }


    public BookReservation getById(Long id){
        Optional<BookReservation> byId = bookReservationRepository.findById(id);
        return byId.orElseThrow(()->new EntityNotFoundException("Book reservation"));
    }

    public BookReservation getByUserId(Long user_id){
        Optional<BookReservation> bookReservationByUserId = bookReservationRepository.findBookReservationByUserId(user_id);
        return bookReservationByUserId.orElseThrow(()->new EntityNotFoundException("Book reservation"));
    }


    public BookReservation create(BookReservationDTO bookReservationDTO){
        BookReservation bookReservation = bookReservationMapper.toEntity(bookReservationDTO);
        BookReservation save = bookReservationRepository.save(bookReservation);
        return save;
    }

    public void delete(Long id){
        getById(id);
        bookReservationRepository.deleteById(id);
    }

    public BookReservation update(Long id, BookReservationDTO bookReservationDTO){
        BookReservation updatedBookReservation = getById(id);
        if(!StringUtils.isEmpty(bookReservationDTO.getBook_id())) {
            updatedBookReservation.setBook(bookService.getById(bookReservationDTO.getBook_id()));
        }if(!StringUtils.isEmpty(bookReservationDTO.getEndDatePlanned())) {
            updatedBookReservation.setEndDatePlanned(bookReservationDTO.getEndDatePlanned());
        }
        return bookReservationRepository.save(updatedBookReservation);

    }



}
