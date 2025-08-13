package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.entity.UsuarioInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioInfoRepository extends JpaRepository<UsuarioInfo,Integer> {

    @Query(value = "select * from usuarios_info where EMAIL=:email",nativeQuery = true)
    public UsuarioInfo getUserByEmail(@Param("email")String email);
}
