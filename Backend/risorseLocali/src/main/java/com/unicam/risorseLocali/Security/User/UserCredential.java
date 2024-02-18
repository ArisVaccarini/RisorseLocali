package com.unicam.risorseLocali.Security.User;

/**
 * Questa classe ha il compito di rappresentare
 * le credenziali di accesso di un utente
 */
public class UserCredential {

    private String username;
    private String password;

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