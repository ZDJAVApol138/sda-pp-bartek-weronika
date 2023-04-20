package pl.sda.bankapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.model.Bank;
import pl.sda.bankapp.model.Customer;
import pl.sda.bankapp.model.StandardAccount;

import java.util.Scanner;

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
}
