package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookRequestDTO;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.model.Status;
import com.berkozmen.library_automation_system.model.mapper.BookRequestMapper;
import com.berkozmen.library_automation_system.repository.BookRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookRequestService {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Autowired
    private BookRequestMapper bookRequestMapper;

    public List<BookRequest> getAllBookRequests(){
        List<BookRequest> all = bookRequestRepository.findAll();
        return all;
    }


    public BookRequest getById(Long id){
        Optional<BookRequest> byId = bookRequestRepository.findById(id);
        return byId.orElseThrow(()->new EntityNotFoundException("Book request"));
    }

    public BookRequest getByUserId(Long user_id){
        Optional<BookRequest> bookRequestByUserId = bookRequestRepository.findBookRequestByUserId(user_id);
        return bookRequestByUserId.orElseThrow(()->new EntityNotFoundException("Book reservation"));
    }


    public BookRequest create(BookRequestDTO bookRequestDTO){
        BookRequest bookRequest = bookRequestMapper.toEntity(bookRequestDTO);
        BookRequest save = bookRequestRepository.save(bookRequest);
        return save;
    }

    public void delete(Long id){
        getById(id);
        bookRequestRepository.deleteById(id);
    }

    public BookRequest updateStatus(Long id, Integer status){
        Optional<BookRequest> byId = bookRequestRepository.findById(id);
        if(!byId.isPresent()){
            throw new EntityNotFoundException("Book request");
        }
        BookRequest updatedBookRequest = byId.get();
        switch(status){
            case 0:
                updatedBookRequest.setStatus(Status.DENIED);
                break;

            case 1:
                updatedBookRequest.setStatus(Status.APPROVED);
                break;

        }
        return bookRequestRepository.save(updatedBookRequest);
    }




}
