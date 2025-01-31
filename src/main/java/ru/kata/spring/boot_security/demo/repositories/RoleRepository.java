package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select r from Role r where r.role in:roles")
    Set<Role> getSetRole(@Param("roles") Set<String> roles);
    //List<Role> findAllById(List<Integer> ids);
}
