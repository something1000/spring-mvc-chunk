package com.gawrych.readinglist.Model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findByConftoken(String token);
    User findByUsername(String username);
    User findById(Long id);

    HashSet<User> findByBanned(Boolean banned);
    @Modifying
    @Query("update User u set u.banned = false, u.ban_date = null where u.username = ?1")
    void unbanUser(String user);
}
