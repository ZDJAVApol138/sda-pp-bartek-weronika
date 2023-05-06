package pl.sda.bankapp.model;

import lombok.*;
import pl.sda.bankapp.exceptions.NotFoundException;
import pl.sda.bankapp.mapper.CSVMapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String pesel;
    private Address address;
    private LocalDate dateOfBirth;
    private int age;
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private List<Account> accounts= new ArrayList<>();

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
        this.accounts= new ArrayList<>();
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

    public String toCsv() {
        return String.join(CSVMapper.DELIMITER, List.of(
                String.valueOf(id), name, surname, phone, email, pesel, String.valueOf(age), dateOfBirth.toString(),
                address.getCity(), address.getStreet(), address.getPostCode())
        );
    }
}
