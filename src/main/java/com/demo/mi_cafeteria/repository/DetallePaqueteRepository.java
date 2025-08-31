package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.entity.DetallePaquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePaqueteRepository extends JpaRepository<DetallePaquete,Integer> {
}
