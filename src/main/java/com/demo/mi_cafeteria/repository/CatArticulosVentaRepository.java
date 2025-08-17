package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.entity.CatArticulosVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatArticulosVentaRepository extends JpaRepository<CatArticulosVenta,Integer> {
}
