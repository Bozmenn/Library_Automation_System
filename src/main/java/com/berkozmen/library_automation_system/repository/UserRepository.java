package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

        // JPQL
        boolean existsByUsername(String username);

        User findByUsername(String username);

        void deleteByUsername(String username);

        Optional<User> findById(Long id);

}
