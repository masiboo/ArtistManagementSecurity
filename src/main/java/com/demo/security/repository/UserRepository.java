package com.demo.security.repository;

import com.demo.security.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserRecord, Long> {
    Optional<UserRecord> findByEmail(String email);
}
