package com.example.offerserver.repository;

import com.example.offerserver.offerservice.task1.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StuffRepository extends JpaRepository<Stuff, UUID> {
}
