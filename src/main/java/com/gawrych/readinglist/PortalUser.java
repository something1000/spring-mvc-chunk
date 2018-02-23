package com.gawrych.readinglist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "users")
public class PortalUser implements UserDetails {

    @NotNull
    @Id
    private String userName;
    @NotNull
    private String userPassword;
    @NotNull
    private String userEmail;

    private String nameAndSurname;
    private String userWWW;
    private SexEnum userSex;
    @Value("img/avatars/default.jpg")
    private String userAvatarURL;
    private String userBannerURL;
    @Temporal(TemporalType.DATE)
    private Date userBanTime;
    private boolean userDeleted;


    public PortalUser(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        nameAndSurname = null;
        userWWW = null;
        userSex = SexEnum.UNDEFINED;
        userBannerURL = null;
        userBanTime = null;
        userDeleted = false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public String getUserWWW() {
        return userWWW;
    }

    public void setUserWWW(String userWWW) {
        this.userWWW = userWWW;
    }

    public SexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(SexEnum userSex) {
        this.userSex = userSex;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    public String getUserBannerURL() {
        return userBannerURL;
    }

    public void setUserBannerURL(String userBannerURL) {
        this.userBannerURL = userBannerURL;
    }

    public Date getUserBanTime() {
        return userBanTime;
    }

    public void setUserBanTime(Date userBanTime) {
        this.userBanTime = userBanTime;
    }

    public boolean isUserDeleted() {
        return userDeleted;
    }

    public void setUserDeleted(boolean userDeleted) {
        this.userDeleted = userDeleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(userBanTime != null) return true;
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return !userDeleted;
    }
}
