package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("")
    public ResponseEntity getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id){
        try {
            Book byId = bookService.getById(id);
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity createNewBook(@RequestBody BookDTO bookDTO){
        Book respBook = bookService.create(bookDTO);
        if(respBook == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable(name = "id") Long id){
        try {
            bookService.delete(id);
        }catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Related book deleted succesfully");
    }

    /*@PutMapping("/{title}")
    public ResponseEntity updateBook(
            @PathVariable String title,
            @RequestBody BookDTO)
    {
        bookService.
    }*/



}
