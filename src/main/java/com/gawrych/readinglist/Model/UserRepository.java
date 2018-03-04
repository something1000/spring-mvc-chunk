package com.gawrych.readinglist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    User findByEmail(String email);
    User findByConftoken(String token);
    User findByUsername(String username);
}
