package fr.xebia.java8.refactoring.data;

public class Address {

    public final String street;
    public final String city;
    public final String postalCode;

    public Address(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String formatForEnveloppe() {
        return street + "\n" + city + " " + postalCode;
    }
}
