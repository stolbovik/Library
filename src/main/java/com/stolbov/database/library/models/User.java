//package com.stolbov.database.library.models;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Entity
//@Table(name = "user")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
//    @SequenceGenerator(name = "userSequence", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1 )
//    @Column(name="id", nullable = false, unique = true)
//    private Long id;
//
//    @Column(name="username", nullable = false)
//    private String username;
//
//    @Column(name="password", nullable = false)
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Collection<Role> roles = new ArrayList<>();
//
//    public User() {
//    }
//
//    public User(Long id, String username, String password, Collection<Role> roles) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }
//
//    public User(String username, String password, Collection<Role> roles) {
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Collection<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Collection<Role> roles) {
//        this.roles = roles;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", roles=" + roles +
//                '}';
//    }
//}
