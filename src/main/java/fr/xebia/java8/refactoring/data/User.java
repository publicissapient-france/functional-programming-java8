package fr.xebia.java8.refactoring.data;

import java.time.LocalDate;

public class User {

    private final String title;

    private final String firstname;

    private final String lastname;

    private String login;

    private String password;

    private LocalDate expireDate;

    private Role role;

    private Address address;

    public User(String title, String firstname, String lastname) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean isExpired() {
        return getExpireDate() != null && getExpireDate().isBefore(LocalDate.now());
    }

    public User withLogin(String login) {
        this.setLogin(login);
        return this;
    }

    public User withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public User withExpireDate(LocalDate expireDate) {
        this.setExpireDate(expireDate);
        return this;
    }

    public User withRole(Role role) {
        this.setRole(role);
        return this;
    }

    public User withAddress(Address address) {
        this.setAddress(address);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "title='" + title + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
