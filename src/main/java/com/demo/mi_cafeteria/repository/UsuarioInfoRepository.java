package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.UsuarioInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioInfoRepository extends JpaRepository<UsuarioInfo,Integer> {
}
