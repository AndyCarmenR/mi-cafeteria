package com.demo.mi_cafeteria.repository;


import com.demo.mi_cafeteria.model.UsuarioPWD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioPWDRepository extends JpaRepository<UsuarioPWD,Integer> {


    @Query(value = "select up from usuarios_pwd up where up.NICKNAME=:nickname")
    public UsuarioPWD getUserByNickname(@Param("nickname") String nickname);
}
