package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.model.Status;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private BookRequestService bookRequestService;

    public void addStatus(Long id, Status status){
        BookRequest byId = bookRequestService.getById(id);
        byId.setStatus(status);
    }

}
