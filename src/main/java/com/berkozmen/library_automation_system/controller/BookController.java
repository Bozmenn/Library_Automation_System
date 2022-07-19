package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.Book;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping("/create")
    public ResponseEntity createNewBook(@RequestBody BookDTO bookDTO){
        Book respBook = bookService.create(bookDTO);
        if(respBook == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book could not added !");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respBook);
    }

}
