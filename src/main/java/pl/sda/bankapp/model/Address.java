package pl.sda.bankapp.model;

import java.util.Objects;

public class Address {
    public String city;
    public String street;
    public String postCode;

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public Address(String city, String street, String postCode) {
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    @Override
    public String toString(){
       // return " City: "+ city + " \n Street: " + street + " \n Postal code: " + postCode;
        //better practise
        return " City: %s \n Street: %s  \n Postal code %s".formatted(city,street,postCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //check: one is equal one
        if (o == null || getClass() != o.getClass()) return false; // check: object is equal other object type
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(postCode, address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, postCode);
    }
}
