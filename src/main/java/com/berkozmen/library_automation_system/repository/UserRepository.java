package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        // JPQL
        boolean existsByUsername(String username);

        User findByUsername(String username);

        void deleteByUsername(String username);

        Optional<User> findById(Long id);

}
