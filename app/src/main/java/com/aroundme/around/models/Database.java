package com.aroundme.around.models;

/**
 * Created by patrickcaruso on 11/5/16.
 */

public class Database {

    public User attemptLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username =  '" + username + "' AND password = '" + password + "'";
        return null;
    }
}
