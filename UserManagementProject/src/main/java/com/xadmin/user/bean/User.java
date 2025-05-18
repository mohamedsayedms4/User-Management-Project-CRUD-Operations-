package com.xadmin.user.bean;

import java.util.Objects;

/**
 * Represents a User entity with basic personal details.
 * Includes common methods like equals, hashCode, and toString.
 */
public class User {

    // Fields representing user properties
    private int id;
    private String name;
    private String email;
    private String country;

    /**
     * Full constructor - used when ID is known (e.g., retrieved from DB).
     *
     * @param id      unique identifier for the user
     * @param name    user's name
     * @param email   user's email address
     * @param country user's country
     */
    public User(int id, String name, String email, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    /**
     * Constructor without ID - used when creating a new user before persisting.
     *
     * @param name    user's name
     * @param email   user's email address
     * @param country user's country
     */
    public User(String name, String email, String country) {
        this.name = name;
        this.email = email;
        this.country = country;
    }

    // Getters and Setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Generates a hash code based on all fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, country);
    }

    /**
     * Compares this user with another for equality based on all fields.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return id == other.id &&
               Objects.equals(name, other.name) &&
               Objects.equals(email, other.email) &&
               Objects.equals(country, other.country);
    }

    /**
     * Returns a string representation of the user object.
     */
    @Override
    public String toString() {
        return "User [id=" + id +
               ", name=" + name +
               ", email=" + email +
               ", country=" + country + "]";
    }
}
