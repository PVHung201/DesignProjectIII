package com.example.test.Reponsitory;

import com.example.test.Entity.Repair;
import com.example.test.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair,Integer> {
}
