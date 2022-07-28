package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    //JPQL
    Optional<BookReservation> findBookReservationByUserId(Long id);

}
