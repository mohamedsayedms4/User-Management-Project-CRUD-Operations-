package com.xadmin.usermanagement.model;

/**
 * The User class represents a user entity in the system.
 * It contains the properties and methods to access and modify user information.
 * 
 * @author Mohamed Sayed
 */
public class User {
    /**
     * Unique identifier for the user.
     * This is typically generated by the database.
     */
    protected int id;
    
    /**
     * The user's full name.
     */
    protected String name;
    
    /**
     * The user's email address.
     * This should be unique for each user.
     */
    protected String email;
    
    /**
     * The user's country of residence.
     */
    protected String country;
    
    /**
     * Default no-argument constructor.
     * Used to create an empty User object before setting properties.
     */
    public User() {
    }
    
    /**
     * Constructor to create a User object without an ID.
     * This is useful when creating new users before the database assigns an ID.
     *
     * @param name    the name of the user
     * @param email   the email of the user
     * @param country the country of the user
     */
    public User(String name, String email, String country) {
        super();
        this.name = name;
        this.email = email;
        this.country = country;
    }

    /**
     * Constructor to create a User object with all fields.
     * Useful for retrieving users from the database where ID is already assigned.
     *
     * @param id      the unique identifier of the user
     * @param name    the name of the user
     * @param email   the email of the user
     * @param country the country of the user
     */
    public User(int id, String name, String email, String country) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     * Typically used when setting the ID after retrieving from the database.
     *
     * @param id the unique identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's name.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the name to set for the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's country.
     *
     * @return the country of the user
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the user's country.
     *
     * @param country the country to set for the user
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
