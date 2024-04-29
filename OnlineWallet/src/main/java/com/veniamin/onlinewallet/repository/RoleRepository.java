package com.veniamin.onlinewallet.repository;

import java.util.Optional;

import com.veniamin.onlinewallet.entity.Role;
import com.veniamin.onlinewallet.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}