package pl.sda.bankapp.service;

import pl.sda.bankapp.model.Address;
import pl.sda.bankapp.model.Bank;
import pl.sda.bankapp.model.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankService {

    public final Scanner scanner;
    public final Bank bank;
    public final AccountFactory accountFactory;

    public BankService(Bank bank) {
        this.scanner = new Scanner(System.in);
        this.bank = bank;
        this.accountFactory = new AccountFactory();
    }

    public void listCustomers(ArrayList<Customer> customers) {
        if (bank.getCustomers().size() == 0) {
            System.out.println("Customers list is empty!");
            return;
        }
        System.out.println("Customer list");
        bank.listCustomer();
    }

    private String getString(String text) {
        System.out.println(text);
        return scanner.nextLine();
    }

    public void createCustomer() {

        String name = getString("Name:");
        String surname = getString("Surame:");
        String phone = getString("Phone:");
        String email = getString("Email:");
        String pesel = getString("Pesel:");
        String city = getString("City:");
        String street = getString("Street:");
        String postCode = getString("Postal Code:");

        String dateOfBirthSt = getString("Date of birth (dd/mm/yyyy:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthSt, formatter);

        Address address = new Address(city, street, postCode);

        Customer customer = new Customer(name,surname,phone,email,pesel,address,dateOfBirth);
        bank.addCustomer(customer);
    }

    public void removeCustomerByPesel() {
        String pesel = scanner.nextLine();
        Customer customer = bank.findCustomer(pesel);
        bank.getCustomers().remove(customer);
    }

    public void getCustomerByPesel() {
        String pesel = scanner.nextLine();
        Customer customer = bank.findCustomer(pesel);
        bank.getCustomers().add(customer);
    }
}
