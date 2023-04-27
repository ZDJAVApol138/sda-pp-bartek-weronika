package pl.sda.bankapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.exceptions.NotFoundException;
import pl.sda.bankapp.model.Account;
import pl.sda.bankapp.model.Bank;
import pl.sda.bankapp.model.Customer;
import pl.sda.bankapp.model.StandardAccount;
import pl.sda.bankapp.utils.AccountNumberGenerator;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private Bank bank;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private BankService bankService;

//    private final Bank bank = Mockito.mock(Bank.class);
//    private final Scanner scanner = Mockito.mock(Scanner.class);
//    private final BankService bankService = new BankService(bank, scanner);


    @BeforeEach
    void reset() {
        Mockito.reset(bank, scanner);
    }

    @Test
    void testListCustomersEmptyList() {
        // given
//        Mockito.when(bank.getCustomers()).thenReturn(Collections.emptyList());

        // when
        bankService.listCustomers();

        // then
        Mockito.verify(bank).getCustomers();
        Mockito.verifyNoMoreInteractions(bank);
    }

    @Test
    void testListCustomersNotEmptyList() {
        // given
        List<Customer> customers = List.of(new Customer());
        Mockito.when(bank.getCustomers()).thenReturn(customers);

        // when
        bankService.listCustomers();

        // then
        Mockito.verify(bank).getCustomers();
        Mockito.verify(bank).listCustomers();
        Mockito.verifyNoMoreInteractions(bank);
    }

    @Test
    void testListCustomerAccounts() {
        // given
        String pesel = "92";
        Customer customer = new Customer();
        customer.addAccount(new StandardAccount(Currency.USD));

        Mockito.doReturn(pesel).when(scanner).nextLine();
        Mockito.when(bank.findCustomer(pesel)).thenReturn(customer);

        // when
        bankService.listAccounts();

        // then
        Mockito.verify(scanner, Mockito.times(1)).nextLine();
        Mockito.verify(bank).findCustomer(pesel);
        Mockito.verifyNoMoreInteractions(bank);
        Mockito.verifyNoMoreInteractions(scanner);
    }

    @Test
    void testRemoveCustomerAccount() {
        // given
        String pesel = UUID.randomUUID().toString();

        Customer customer = new Customer();
        Account account = new StandardAccount(Currency.EUR);
        String accountNumber = account.getAccountNumber();
        customer.addAccount(account);

        Mockito.when(scanner.nextLine()).thenReturn(pesel, accountNumber);
        Mockito.when(bank.findCustomer(pesel)).thenReturn(customer);

        // when
        bankService.removeCustomerAccount();

        // then
        Mockito.verify(scanner, Mockito.times(2)).nextLine();
        Mockito.verify(bank).findCustomer(pesel);
        Mockito.verifyNoMoreInteractions(bank, scanner);
    }

    @Test
    void testRemoveCustomerAccount2() {
        // given
        String pesel = UUID.randomUUID().toString();

        Customer customer = Mockito.mock(Customer.class);
        Account account = new StandardAccount(Currency.EUR);
        String accountNumber = account.getAccountNumber();

        Mockito.when(scanner.nextLine()).thenReturn(pesel, accountNumber);
        Mockito.when(bank.findCustomer(pesel)).thenReturn(customer);
        Mockito.when(customer.getAccount(accountNumber)).thenReturn(account);
        Mockito.when(customer.removeAccount(Mockito.any())).thenReturn(false);

        // when
        bankService.removeCustomerAccount();

        // then
        Mockito.verify(scanner, Mockito.times(2)).nextLine();
        Mockito.verify(bank).findCustomer(pesel);

        Mockito.verify(customer).getAccount(accountNumber);
        Mockito.verify(customer).removeAccount(Mockito.any());
        Mockito.verifyNoMoreInteractions(bank, scanner, customer);
    }


    @Test
    void testRemoveCustomerAccount3() {
        // given
        Bank bank = new Bank("Test Bank");
        BankService bankService = new BankService(bank, scanner);

        Customer customer = new Customer();
        String pesel = UUID.randomUUID().toString();
        customer.setPesel(pesel);

        bank.addCustomer(customer);

        Account account = new StandardAccount(Currency.EUR);
        customer.addAccount(account);

        String accountNumber = account.getAccountNumber();
        Mockito.when(scanner.nextLine()).thenReturn(pesel, accountNumber);

        Assertions.assertTrue(customer.getAccounts().contains(account));
        Assertions.assertEquals(1, customer.getAccounts().size());

        // when
        bankService.removeCustomerAccount();

        // then
        Assertions.assertFalse(customer.getAccounts().contains(account));
        Assertions.assertTrue(customer.getAccounts().isEmpty());
        Mockito.verify(scanner, Mockito.times(2)).nextLine();
        Mockito.verifyNoMoreInteractions(scanner);
    }

    @Test
    void testRemoveCustomerAccountCustomerNotFound() {
        // given
        Bank bank = new Bank("Test Bank");
        BankService bankService = new BankService(bank, scanner);
        String pesel = UUID.randomUUID().toString();

        Mockito.when(scanner.nextLine()).thenReturn(pesel);

        // when
        Executable executable = bankService::removeCustomerAccount;

        // then
        Assertions.assertThrows(NotFoundException.class, executable);
        Mockito.verify(scanner).nextLine();
        Mockito.verifyNoMoreInteractions(scanner);
    }

    @Test
    void testRemoveCustomerAccountAccountNotFound() {
        // given
        String pesel = UUID.randomUUID().toString();
        String accountNumber = AccountNumberGenerator.generate();

        Mockito.when(scanner.nextLine()).thenReturn(pesel, accountNumber);
        Mockito.when(bank.findCustomer(pesel)).thenReturn(new Customer());

        // when
        Executable executable = bankService::removeCustomerAccount;

        // then
        Assertions.assertThrows(NotFoundException.class, executable);
        Mockito.verify(scanner, Mockito.times(2)).nextLine();
        Mockito.verify(bank).findCustomer(pesel);
        Mockito.verifyNoMoreInteractions(scanner, bank);
    }

    @Test
    void testGetCustomerByPeselCustomerNotExists() {
        // given
        String pesel = UUID.randomUUID().toString();
        Bank bank = new Bank("Test Bank");
        BankService bankService = new BankService(bank, scanner);

        Mockito.when(scanner.nextLine()).thenReturn(pesel);

        // when
        Executable executable = bankService::getCustomerByPesel;

        // then
        Assertions.assertThrows(NotFoundException.class, executable);
        Mockito.verify(scanner).nextLine();
        Mockito.verifyNoMoreInteractions(scanner);
    }

    @Test
    void testGetCustomerByPeselCustomerExists() {
        // given
        String pesel = UUID.randomUUID().toString();
        Mockito.when(scanner.nextLine()).thenReturn(pesel);
        Mockito.when(bank.findCustomer(pesel)).thenReturn(new Customer());

        // when
        bankService.getCustomerByPesel();

        // then
        Mockito.verify(scanner).nextLine();
        Mockito.verify(bank).findCustomer(pesel);
        Mockito.verifyNoMoreInteractions(scanner, bank);
    }
}