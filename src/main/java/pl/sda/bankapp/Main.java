package pl.sda.bankapp;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.model.*;
import pl.sda.bankapp.service.BankService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bank bank1 = new Bank("Alior");
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService(bank1);

        String options = """
                1 - Create customer
                2 - Remove customer
                3 - Find customer
                4 - List customers
                5 - Create Account
                6 - Delete Account
                7 - List Accounts
                8 - Exit
                """;

        String userInput;
        do {
            System.out.println(options);
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> bankService.createCustomer();
                case "2" -> bankService.removeCustomerByPesel();
                case "3" -> bankService.getCustomerByPesel();
                case "4" -> bankService.listCustomers(bank1.getCustomers());
                case "5" -> bankService.createCustomerAccount();
                case "6" -> bankService.removeCustomerAccount();
                case "7" -> bankService.listAccounts();
                case "8" -> System.out.println("Exited");
                default -> System.out.println("Invalid input");
            }

        } while (!"8".equals(userInput));

    }
}
