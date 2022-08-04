package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {

    Optional<BookRequest> findBookRequestByUserId(Long id);



}
