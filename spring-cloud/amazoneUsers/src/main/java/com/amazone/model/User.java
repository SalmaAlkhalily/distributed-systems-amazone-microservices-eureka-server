package com.amazone.model;


import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class User {



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
    public User(){}
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String firstname;

    private String lastname;
    private String email;
    private String password;

}
