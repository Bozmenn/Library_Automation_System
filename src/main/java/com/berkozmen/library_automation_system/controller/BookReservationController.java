package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.repository.BookReservationRepository;
import com.berkozmen.library_automation_system.service.BookReservationService;
import com.berkozmen.library_automation_system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookReservations")
@Api(value = "Book Reservation documentation")
public class BookReservationController {

    @Autowired
    private BookReservationService bookReservationService;


    @ApiOperation(value = "Book Reservation list method")
    @GetMapping("")
    public ResponseEntity getAllBookReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(bookReservationService.getAllBookReservations());
    }
    @ApiOperation(value = "Book Reservation get by user id method")
    @GetMapping("/{id}")
    public ResponseEntity getBookReservationByUserId(@PathVariable(name = "id") Long id){
        BookReservation byUserId = bookReservationService.getByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byUserId);
    }
    @ApiOperation(value = "Book Reservation create method")
    @PostMapping("/create")
    public ResponseEntity createNewBookReservation(@RequestBody BookReservationDTO bookReservationDTO){
        BookReservation bookReservation = bookReservationService.create(bookReservationDTO);
        if(bookReservation == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book reservation could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book reservation successfully created");
    }
    @ApiOperation(value = "Book Reservation delete method")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable(name = "id") Long id){
        bookReservationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book deleted succesfully");
    }
    @ApiOperation(value = "Book Reservation update method")
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(
            @PathVariable Long id,
            @RequestBody BookReservationDTO bookReservationDTO)
    {
        bookReservationService.update(id, bookReservationDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Book reservation succesfully updated");
    }

}
