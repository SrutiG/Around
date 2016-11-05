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
    private String description;
    private String university;
    private String interests;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public void setDescription(String description) { this.description = description; }

    public void setUniversity(String university) { this.university = university; }

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

    public String getDescription() { return description; }

    public String getUniversity() { return university; }

    public String getInterests() { return interests; }

}
