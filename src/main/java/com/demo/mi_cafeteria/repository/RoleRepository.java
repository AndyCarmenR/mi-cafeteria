package com.demo.mi_cafeteria.repository;

import com.demo.mi_cafeteria.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {

}
