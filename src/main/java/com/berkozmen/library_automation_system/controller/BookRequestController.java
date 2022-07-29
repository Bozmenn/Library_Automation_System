package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookRequestDTO;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookRequests")
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;

    @GetMapping("")
    public ResponseEntity getAllBookRequests(){
        return ResponseEntity.status(HttpStatus.OK).body(bookRequestService.getAllBookRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookRequestByUserId(@PathVariable(name = "id") Long id){
        BookRequest byUserId = bookRequestService.getByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byUserId);
    }

    @PostMapping("/create")
    public ResponseEntity createNewBookRequest(@RequestBody BookRequestDTO bookRequestDTO){
        BookRequest bookRequest = bookRequestService.create(bookRequestDTO);
        if(bookRequest == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book request could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book request successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookRequest(@PathVariable(name = "id") Long id){
        bookRequestService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book request deleted succesfully");
    }

    @PutMapping("/update_status/{id}/{status}")
    public ResponseEntity updateBookRequestStatus(
            @PathVariable Long id,
            @PathVariable Integer status)
    {
        bookRequestService.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body("Book request succesfully updated");
    }


}
