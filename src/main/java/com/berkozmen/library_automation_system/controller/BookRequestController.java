package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookRequestDTO;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.service.BookRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookRequests")
@Api(value = "Book Request Api documentation")
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;
    @ApiOperation(value = "Book Request list method")
    @GetMapping("")
    public ResponseEntity getAllBookRequests(){
        return ResponseEntity.status(HttpStatus.OK).body(bookRequestService.getAllBookRequests());
    }
    @ApiOperation(value = "Book Request get by user id method")
    @GetMapping("/{id}")
    public ResponseEntity getBookRequestByUserId(@PathVariable(name = "id") Long id){
        BookRequest byUserId = bookRequestService.getByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byUserId);
    }
    @ApiOperation(value = "Book Request create method")
    @PostMapping("/create")
    public ResponseEntity createNewBookRequest(@RequestBody BookRequestDTO bookRequestDTO){
        BookRequest bookRequest = bookRequestService.create(bookRequestDTO);
        if(bookRequest == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Book request could not created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Book request successfully created");
    }
    @ApiOperation(value = "Book Request delete method")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookRequest(@PathVariable(name = "id") Long id){
        bookRequestService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related book request deleted succesfully");
    }
    @ApiOperation(value = "Book Request update method")
    @PutMapping("/update_status/{id}/{status}")
    public ResponseEntity updateBookRequestStatus(
            @PathVariable Long id,
            @PathVariable Integer status)
    {
        bookRequestService.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body("Book request succesfully updated");
    }


}
