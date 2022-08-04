package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    //JPQL
    Optional<BookReservation> findBookReservationByUserId(Long id);

}
