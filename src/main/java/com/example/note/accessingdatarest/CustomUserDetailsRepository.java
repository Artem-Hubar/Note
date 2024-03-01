package com.example.note.accessingdatarest;

import com.example.note.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserDetailsRepository extends JpaRepository<UsersEntity, String> {
    Optional<UsersEntity> findByUsername(String username);
}
