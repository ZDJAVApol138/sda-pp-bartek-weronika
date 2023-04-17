package pl.sda.bankapp;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.model.Account;
import pl.sda.bankapp.model.Address;
import pl.sda.bankapp.model.Customer;
import pl.sda.bankapp.model.StandardAccount;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
//hashcode oraz equals
        Address address1 = new Address("Amsterdam","Amsterdamska","00-001");
        Address address2 = new Address("Barcelona","Barcelońska","00-002");
        System.out.println(address1.toString());

        Customer customer1 = new Customer("Alicja","Artemska","1111111111","aa@aa.aa",1,11,address1,"111111");
        StandardAccount acc1 = new StandardAccount(123,1234, Currency.EUR);

        System.out.println(customer1.toString());
    }
}
