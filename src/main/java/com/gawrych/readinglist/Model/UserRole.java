package com.gawrych.readinglist;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole implements Serializable{

    @Id
    @NotEmpty
    @Column(name="role")
    private String role;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
