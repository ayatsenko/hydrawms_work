package com.idltd.hydramob.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator( name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    private long id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "jwt", nullable = true)
    private String jwt;
    @Column(name = "USER_SURNAME", nullable = true)
    public String user_surname;
    @Column(name = "USER_EMAIL", nullable = true)
    public String user_email;
    @Column(name = "USER_MAINPHONE", nullable = true)
    public String user_mainphone;
    @Column(name = "USER_NONMAINPHONE", nullable = true)
    public String user_nonmainphone;
    @Column(name = "USER_STATUS_ID", nullable = true)
    public Long user_status_id;
    @Column(name = "USER_NOTE", nullable = true)
    public String user_note;
    @Column(name = "POS_ID", nullable = true)
    public Long pos_id;
    @Column(name = "TELEGRAM_CHATID", nullable = false)
    public Long telegram_chatid;

    @ManyToMany
    @JoinTable(name = "users_locations",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "loc_id"))
    private Set<Location> locations;

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Set<Role> getRoles() {return roles;}

    public void setRoles(Set<Role> roles) { this.roles = roles;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    public void setUser_surname (String user_surname) {
        this.user_surname = user_surname;
    }
    public void setUser_email (String user_email) {
        this.user_email = user_email;
    }
    public void setUser_mainphone(String user_mainphone) {this.user_mainphone = user_mainphone;}
    public void setUser_nonmainphone(String user_nonmainphone) {
        this.user_nonmainphone = user_nonmainphone;
    }
    public void setUser_status_id(Long user_status_id) {
        this.user_status_id = user_status_id;
    }
    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }
    public void setPos_id(Long pos_id) {
        this.pos_id = pos_id;
    }
    public void setTelegram_chatid(Long telegram_chatid) {
        this.telegram_chatid = telegram_chatid;
    }

    protected User(){}
    public User(String name, String pass) {
        this.username = name;
        this.password = pass;
    }
    public User(long id, String name, String pass) {
        this.id=id;
        this.username = name;
        this.password = pass;
    }
}