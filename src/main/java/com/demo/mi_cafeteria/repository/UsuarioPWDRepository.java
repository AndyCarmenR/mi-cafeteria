package com.demo.mi_cafeteria.repository;


import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioPWDRepository extends JpaRepository<UsuarioPWD,Integer> {


    @Query(value = "select * from usuarios_pwd up where up.NICKNAME=:nickname", nativeQuery = true)
    public UsuarioPWD getUserByNickname(@Param("nickname") String nickname);
}
