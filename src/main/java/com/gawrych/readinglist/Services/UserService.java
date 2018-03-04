package com.gawrych.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByConftoken(String token){
        return userRepository.findByConftoken(token);
    }



    public void saveUser(User user){

        Set<UserRole> d = new HashSet<>();
        if(roleRepository.findByRole("ROLE_USER")== null){
            UserRole x = new UserRole();
            x.setRole("ROLE_USER");
            roleRepository.save(x);

            d.add(roleRepository.findByRole("ROLE_USER"));
        }

        if(user.getUsername() == "admin"){
            UserRole c = new UserRole();
            c.setRole("ROLE_ADMIN");
            d.add(roleRepository.findByRole("ROLE_ADMIN"));
        }
        user.setRoles(d);
        userRepository.save(user);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User us = userRepository.findByUsername(username);
        if(us == null){
            return null;
        }

        return new org.springframework.security.core.userdetails.User(username, us.getPassword(),us.getAuthorities() );
    }
}
