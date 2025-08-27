package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.entity.CatExtras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatExtrasRepository extends JpaRepository<CatExtras,Integer> {
}
