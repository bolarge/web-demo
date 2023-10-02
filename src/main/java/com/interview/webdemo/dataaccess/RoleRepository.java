package com.interview.webdemo.dataaccess;

import com.interview.webdemo.domain.Role;
import com.interview.webdemo.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(UserRole userRole);
}
