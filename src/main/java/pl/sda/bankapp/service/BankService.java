package pl.sda.bankapp.service;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.model.Account;
import pl.sda.bankapp.model.Address;
import pl.sda.bankapp.model.Bank;
import pl.sda.bankapp.model.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

        String dateOfBirthSt = getString("Date of birth (dd/mm/yyyy):");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthSt, formatter);

        Address address = new Address(city, street, postCode);

        Customer customer = new Customer(name, surname, phone, email, pesel, address, dateOfBirth);
        bank.addCustomer(customer);
    }
    public void listAccounts() {
        String pesel = getString("Pesel:");
        Customer customer =bank.findCustomer(pesel);
        System.out.println("Accounts list: ");
        customer.listAccount();
    }

    public void removeCustomerByPesel() {
        String pesel = getString("Pesel:");
        boolean deleted = bank.deleteCustomer(pesel);
        System.out.printf("Customer deleted: %s\n",pesel);
    }

    public void getCustomerByPesel() {
        String pesel = getString("Pesel:");
        Customer customer = bank.findCustomer(pesel);
        System.out.printf("Customer obtained: %s\n",customer);
    }

    public void removeCustomerAccount(){
        String pesel = getString("Pesel:");
        Customer customer = bank.findCustomer(pesel);

        String accountNumber = getString("Account number:");

        Account accountToRemove = customer.getAccount(accountNumber);

        boolean removed = customer.deleteAccount(accountToRemove);
        System.out.printf("Account removed %s\n",accountToRemove);
    }


    public void createCustomerAccount() {
        String pesel = getString("Pesel:");
        Customer customer = bank.findCustomer(pesel);

        String message = "Currency: " + Arrays.toString(Currency.values());
        String currencySt = getString(message).toUpperCase();

        String accMessage = "Account Type: " + Arrays.toString(AccountType.values());
        String accountTypeSt = getString(accMessage).toUpperCase();

        Currency currency = Currency.valueOf(currencySt);
        AccountType accountType = AccountType.valueOf(accountTypeSt);

        Account account = accountFactory.createAccount(accountType, currency);

        customer.addAccount(account);
    }




}
