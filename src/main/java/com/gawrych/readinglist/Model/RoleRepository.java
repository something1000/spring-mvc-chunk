package com.gawrych.readinglist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, String> {
    UserRole findByRole(String role);
}
