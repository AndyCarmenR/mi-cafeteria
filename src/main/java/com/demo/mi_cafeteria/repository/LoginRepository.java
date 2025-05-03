package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer> {

}
