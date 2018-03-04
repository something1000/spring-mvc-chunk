package com.gawrych.readinglist.Services;

import com.gawrych.readinglist.Model.RoleRepository;
import com.gawrych.readinglist.Model.User;
import com.gawrych.readinglist.Model.UserRepository;
import com.gawrych.readinglist.Model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService{



    private UserRepository userRepository;
    private UserRoleService userRoleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByConftoken(String token){
        return userRepository.findByConftoken(token);
    }

    public User findById(Long id){return userRepository.findById(id); }

    public Set<User> findByBanned(){return userRepository.findByBanned(true);}


    public void saveUser(User user){

        if(user.getUsername().equals("admin")){
            Set<UserRole> d = new HashSet<>();

            userRoleService.newUserRole("ROLE_ADMIN");
            d.add(userRoleService.findByRole("ROLE_ADMIN"));
            user.setRoles(d);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }
    public void updateUser(User user){
        userRepository.save(user);
    }


    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),true,true,true, !user.isBanned(), user.getAuthorities() );
    }
}
