package com.gawrych.readinglist.Services;

import com.gawrych.readinglist.Model.RoleRepository;
import com.gawrych.readinglist.Model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleService {

    @Autowired
    private RoleRepository roleRepository;

    public UserRole findByRole(String role){
        return roleRepository.findByRole(role);
    }

    public void newUserRole(String role){
        if(findByRole(role) == null){
            roleRepository.save(new UserRole(role));
        }
    }


}
