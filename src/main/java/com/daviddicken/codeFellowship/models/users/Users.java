package com.daviddicken.codeFellowship.models.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;


@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public String userName;
    public String password;
    public String firstname;
    public String lastname;
    public String bio;
    public java.sql.Date dob;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Post> posts = new ArrayList<>();

    //================== Many to Many ==================
    @ManyToMany(cascade = CascadeType.REMOVE)

    @JoinTable(
            name="friends",
            joinColumns = { @JoinColumn(name="user1")},
            inverseJoinColumns = {@JoinColumn(name="user2")}
    )

    public Set<Users> stalkers = new HashSet<>();

    @ManyToMany(mappedBy = "stalkers")
    public Set<Users> followed = new HashSet<>();

    //================= Constructors =============
    public  Users() {};

    public Users(String userName, String password, String firstname, String lastname, String bio, Date dob) {
        this.userName = userName;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.dob = dob;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
