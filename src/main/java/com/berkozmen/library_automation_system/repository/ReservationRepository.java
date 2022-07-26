package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation , Long> {

}
