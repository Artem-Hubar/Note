package com.example.note.accessingdatarest;

import com.example.note.entities.AuthoritiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<AuthoritiesEntity, Integer> {

    Optional<Collection<AuthoritiesEntity>> findAllByUsersByUser_Username(String usersByUser_username);
}
