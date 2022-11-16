package com.example.myapplication.Model;

import java.io.Serializable;

public class User implements Serializable {
   private String id;
   private String name;
   private String password;
   private String phonenumber;
   private int sodu;


    public User() {
    }

    public User(String id, String name, String password, String phonenumber, int sodu) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phonenumber = phonenumber;
        this.sodu = sodu;
    }

    public User(String name, String password, String phonenumber, int sodu) {
        this.name = name;
        this.password = password;
        this.phonenumber = phonenumber;
        this.sodu = sodu;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }


}

