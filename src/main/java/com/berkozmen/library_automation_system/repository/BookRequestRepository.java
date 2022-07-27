package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {


}
