package pl.sda.bankapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class Customer {
    private String name;
    private String surname;
    private String pesel;
    private String email;
    private long id;
    private int age;
    private Address address;
    private ArrayList<Account> accounts;

    public Customer(String name, String surname, String pesel, String email, long id, int age, Address address, String phone) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.email = email;
        this.id = id;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public boolean deleteAccount(Account account) {
        return accounts.remove(account);
    }

    public void listAccount() {
        for (Account account : accounts
        ) {
            System.out.println(account.toString());

        }
    }

    @Override
    public String toString() {
        return " name=" + name +
                "  surname=" + surname +
                "  pesel=" + pesel +
                "\n email=" + email +
                " id=" + id +
                "  age=" + age +
                "\n" + address +
                "\n phone=" + phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && age == customer.age && Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && Objects.equals(pesel, customer.pesel) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address) && Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, pesel, email, id, age, address, phone);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;


}
