//package com.stolbov.database.library.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "role")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
//    @SequenceGenerator(name = "roleSequence", sequenceName = "role_sequence", allocationSize = 1, initialValue = 1 )
//    @Column(name="id", nullable = false, unique = true)
//    private Long id;
//
//    @Column(name="name", nullable = false)
//    private String name;
//
//    public Role() {
//    }
//
//    public Role(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Role(String name) {
//        this.name = name;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
