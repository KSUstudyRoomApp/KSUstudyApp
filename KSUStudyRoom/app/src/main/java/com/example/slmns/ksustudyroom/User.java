package com.example.slmns.ksustudyroom;

public class User {
    String id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String username;
    String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(){
        String id;
        String firstName;
        String lastName;
        String email;
        String phone;
        String username;
        String password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
