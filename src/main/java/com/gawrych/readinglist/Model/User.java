package com.gawrych.readinglist.Model;

import com.gawrych.readinglist.Converters.LocalDateAttributeConverter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NotEmpty
    @Column(name="username", unique = true)
    private String username;

    @Column(name="password")
    private String password;


    @Transient
    private String reapeatpassword;

    @NotEmpty
    @Column(name="email",nullable = false, unique = true)
    private String email;

    @Column(name="enabled")
    @Value("true")
    private boolean enabled;

    @Column(name="banned")
    @Value("false")
    private boolean banned;

    @Value("null")
    @Column(name="ban_date", nullable = true)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate ban_date;

    @Value("null")
    @Column(name="conf_token")
    private String conftoken;


    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "role"))

    private Set<UserRole> roles;

   public Set<UserRole> getRoles(){
        return roles;
    }
    public void setRoles(Set<UserRole> roles){
        this.roles = roles;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public LocalDate getBan_date() {
        return ban_date;
    }

    public void setBan_date(LocalDate ban_date) {
        this.ban_date = ban_date;
    }

    public String getConftoken() {
        return conftoken;
    }

    public void setConftoken(String conftoken) {
        this.conftoken = conftoken;
    }

    public String getReapeatpassword() {
        return reapeatpassword;
    }

    public void setReapeatpassword(String reapeatpassword) {
        this.reapeatpassword = reapeatpassword;
    }


    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        for(UserRole u : getRoles()){
            auth.add(new SimpleGrantedAuthority(u.getRole()));
        }
        return auth;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
