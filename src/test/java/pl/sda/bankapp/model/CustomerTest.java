package pl.sda.bankapp.model;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.bankapp.enums.Currency;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

class CustomerTest {

    @Test
    void testConstructorLogic() {
        //given
        Faker faker = new Faker();
        Name name = faker.name();


        String firstName = name.firstName(); // Emory
        String lastName = name.lastName(); // Barton
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String email = faker.internet().emailAddress();
        String pesel = faker.idNumber().valid();
        LocalDate dateOfBirth = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int expectedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();

        String city = faker.address().city();
        String postCode = faker.address().zipCode();
        String street = faker.address().streetAddress();
        Address address = new Address(city, street, postCode);

        //when
        Customer customer = new Customer(
                firstName, lastName, phoneNumber, email, pesel, address, dateOfBirth
        );
        int actualAge = customer.getAge();

        //then
        Assertions.assertEquals(expectedAge, actualAge);

    }


    @Test
    void testAddAccountSuccess() {
        //given
        Customer customer = new Customer();
        Account account = new SavingAccount(Currency.EUR);
        String accountNumber = account.getAccountNumber();
        List<Account> accounts = customer.getAccounts();

        //when
        boolean added = customer.addAccount(account);

        //then
        Assertions.assertTrue(added);
        Assertions.assertTrue(accounts.contains(account));
    }


    @Test
    void testRemoveAccountSuccess() {
        //given
        Customer customer = new Customer();
        Account account = new SavingAccount(Currency.EUR);
        List<Account> accounts = customer.getAccounts();
        boolean added = customer.addAccount(account);

        //when
        boolean removed = customer.removeAccount(account);

        //then
        Assertions.assertTrue(removed);
        Assertions.assertFalse(accounts.contains(account));
    }




}