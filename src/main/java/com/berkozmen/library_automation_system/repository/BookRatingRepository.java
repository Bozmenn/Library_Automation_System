package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {


}
