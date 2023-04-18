package pl.sda.bankapp.service;

import pl.sda.bankapp.model.Bank;

import java.util.Scanner;

public class BankService {


    public final Scanner scanner ;
    public final Bank bank;
    public final AccountFactory accountFactory ;

    public BankService(Bank bank) {
        this.scanner = new Scanner(System.in);
        this.bank = bank;
        this.accountFactory = new AccountFactory();
    }
}
