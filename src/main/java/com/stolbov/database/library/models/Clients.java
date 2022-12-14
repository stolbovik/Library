package com.stolbov.database.library.models;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Clients {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "clientsSequence")
    @SequenceGenerator(name = "clientsSequence", sequenceName = "clients_sequence", allocationSize = 1, initialValue = 1 )
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name= "father_name", nullable = false)
    private String fatherName;

    @Column(name="passport_seria", nullable = false)
    private String passportSeria;

    @Column(name="passport_num", nullable = false)
    private String passportNum;

    public Clients() {
    }

    public Clients(String firstName, String lastName, String fatherName,
                   String passportSeria, String passportNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.passportSeria = passportSeria;
        this.passportNum = passportNum;
    }

    public Clients(Long id, String firstName, String lastName, String fatherName,
                   String passportSeria, String passportNum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.passportSeria = passportSeria;
        this.passportNum = passportNum;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", passportSeria='" + passportSeria + '\'' +
                ", passportNum='" + passportNum + '\'' +
                '}';
    }
}

