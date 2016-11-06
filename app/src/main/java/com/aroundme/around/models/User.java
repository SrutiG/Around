package com.aroundme.around.models;

/**
 * Created by Sruti on 11/5/16.
 */

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String status;
    private String interests;
    private String description;
    private String image;



    public User(String firstName, String lastName, String email, String password, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInterests(String interests) { this.interests = interests; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getInterests() {
        return interests;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {

        return description;
    }

}
