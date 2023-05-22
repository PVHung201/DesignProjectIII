package com.example.test.Reponsitory;

import com.example.test.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Integer> {
    List<Type> findAllByNameContaining(String key);
}
