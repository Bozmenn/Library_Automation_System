package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.repository.BookReservationRepository;
import com.berkozmen.library_automation_system.service.BookReservationService;
import com.berkozmen.library_automation_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookReservations")
public class BookReservationController {

    @Autowired
    private BookReservationService bookReservationService;



    @GetMapping("")
    public ResponseEntity getAllBookReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(bookReservationService.getAllBookReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookReservationByUserId(@PathVariable(name = "id") Long id){
        BookReservation byUserId = bookReservationService.getByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byUserId);
    }

    @PostMapping("/create")
    public ResponseEntity createNewBookReservation(@RequestBody BookReservationDTO bookReservationDTO){
        BookReservation bookReservation = bookReservationService.create(bookReservationDTO);
        if(bookReservation == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book reservation could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book reservation successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable(name = "id") Long id){
        bookReservationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book deleted succesfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(
            @PathVariable Long id,
            @RequestBody BookReservationDTO bookReservationDTO)
    {
        bookReservationService.update(id, bookReservationDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Book reservation succesfully updated");
    }

}
