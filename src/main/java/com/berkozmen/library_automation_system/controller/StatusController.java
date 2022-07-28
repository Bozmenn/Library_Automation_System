package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.model.Status;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PutMapping("/{id}")
    public ResponseEntity addStatusToRequest(
            @PathVariable Long id,
            @RequestBody Status status)
    {
        statusService.addStatus(id,status);
        return ResponseEntity.status(HttpStatus.OK).body("Book request status succesfully updated");
    }

}
