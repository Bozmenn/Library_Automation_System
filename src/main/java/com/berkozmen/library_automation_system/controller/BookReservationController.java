package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
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

/*    @GetMapping("/{id}")
    public ResponseEntity getBookReservationById(@PathVariable(name = "id") Long id){
        bookReservationService.get
        bookService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getById(id));
    }*/

/*    @PostMapping("/create")
    public ResponseEntity createNewBook(@RequestBody BookDTO bookDTO){
        Book respBook = bookService.create(bookDTO);
        if(respBook == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book successfully created");
    }*/

/*    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable(name = "id") Long id){
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book deleted succesfully");
    }

    @PutMapping("/update/{title}")
    public ResponseEntity updateBook(
            @PathVariable String title,
            @RequestBody BookDTO bookDTO)
    {
        bookService.update(title, bookDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Book succesfully updated");
    }*/

}
