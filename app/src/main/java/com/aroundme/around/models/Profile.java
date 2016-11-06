package com.aroundme.around.models;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class Profile {

    private String firstName, lastName, status, interests, dateofbirth;
    private String img;

    public Profile(String firstName, String lastName, String status, String interests, String dateofbirth, String img) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.interests = interests;
        this.dateofbirth = dateofbirth;
        this.img = img;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }

    public String getInterests() {
        return interests;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public String getImg() {
        return img;
    }
}
