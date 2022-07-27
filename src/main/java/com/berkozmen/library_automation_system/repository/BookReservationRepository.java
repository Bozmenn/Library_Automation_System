package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    //JPQL
    List<BookReservation> findBookReservationByUserId(Long id);

}
