package com.harry.store.repository;

import com.harry.store.model.Role;
import com.harry.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
