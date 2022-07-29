package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookFeedbackDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookFeedback;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.model.mapper.BookFeedbackMapper;
import com.berkozmen.library_automation_system.service.BookFeedbackService;
import com.berkozmen.library_automation_system.service.BookService;
import com.berkozmen.library_automation_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookFeedbacks")
public class BookFeedbackController {

    @Autowired
    private BookFeedbackService bookFeedbackService;


    @GetMapping("")
    public ResponseEntity getAllBookFeedbacks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookFeedbackService.getAllBookFeedbacks());
    }

    @GetMapping("/byUserId/{user_id}")
    public ResponseEntity getBookFeedbackByUserId(@PathVariable(name = "user_id") Long id){
        BookFeedback byUserId = bookFeedbackService.getByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byUserId);
    }

    @GetMapping("/byBookId/{book_id}")
    public ResponseEntity getBookFeedbackByBookId(@PathVariable(name = "book_id") Long id){
        BookFeedback byBookId = bookFeedbackService.getByBookId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byBookId);
    }


    @PostMapping("/create")
    public ResponseEntity createNewBookFeedback(@RequestBody BookFeedbackDTO bookFeedbackDTO){
        BookFeedback bookFeedback = bookFeedbackService.create(bookFeedbackDTO);
        if(bookFeedback == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book feedback could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book feedback successfully created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookFeedback(@PathVariable(name = "id") Long id){
        bookFeedbackService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book feedback deleted succesfully");
    }

}
