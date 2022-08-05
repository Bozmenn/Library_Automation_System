package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("")
    public ResponseEntity getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity getBookById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getById(id));
    }

    @GetMapping("/getBookByTitle/{title}")
    public ResponseEntity getBookByTitle(@PathVariable(name = "title") String title){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getByTitle(title));
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
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDTO bookDTO)
    {
        bookService.update(id, bookDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Book successfully updated");
    }

}
