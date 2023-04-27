package pl.sda.bankapp.model;

import lombok.*;
import pl.sda.bankapp.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Customer {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String pesel;
    private Address address;
    private LocalDate dateOfBirth;
    private final ArrayList<Account> accounts= new ArrayList<>();
    private int age;


    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }

    public Customer(String name, String surname, String phone,
                    String email, String pesel, Address address, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.pesel = pesel;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void listAccount() {
        for (Account account : accounts
        ) {
            System.out.println(account.toString());

        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.stream()
                .filter(c -> c.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Customer not found."));
    }

}
