package pl.sda.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.bankapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private String name;
    private String surname;
    private String pesel;
    private String email;

    private long id;
    private int age;
    private Address address;
    private final ArrayList<Account> accounts= new ArrayList<>();
    private String phone;

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

    public Account getAccount(String accountNumber) {
        return accounts.stream()
                .filter(c -> c.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Customer not found."));
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



}
