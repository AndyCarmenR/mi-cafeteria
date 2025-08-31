package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.entity.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete,Integer> {
}
