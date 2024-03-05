package com.example.becommercews.repository;

import com.example.becommercews.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("SELECT s FROM Store s WHERE s.name = :name")
    Optional<Store> findByName(String name);
}
