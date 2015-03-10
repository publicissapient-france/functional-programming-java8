package fr.xebia.java8.refactoring.data;


import fr.xebia.java8.refactoring.other.CurrentDate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class User {

    private final Title title;

    private final String firstname;

    private final String lastname;

    private String login;

    private String password;

    private LocalDate expireDate;

    private Role role;

    private Address address;

    private Optional<Address> optionalAddress = Optional.empty();

    private LocalDate birthday;

    public User(Title title, String firstname, String lastname) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean isExpired() {
        return getExpireDate() != null && getExpireDate().isBefore(CurrentDate.get());
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

    public User withBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Title getTitle() {
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

    public Optional<Address> getOptionalAddress() {
        return optionalAddress;
    }

    public void setAddress(Address address) {
        this.address = address;
        this.optionalAddress = Optional.ofNullable(address);
    }

    @Override
    public String toString() {
        return "User{" +
                "title='" + title + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public int age() {
        return Period.between(birthday, CurrentDate.get()).getYears();
    }
}
