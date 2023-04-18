package pl.sda.bankapp.service;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.model.Account;
import pl.sda.bankapp.model.PremiumAccount;
import pl.sda.bankapp.model.SavingAccount;
import pl.sda.bankapp.model.StandardAccount;


import java.util.UUID;

public class AccountFactory {

    public Account createAccount(AccountType accountType, Currency currency){
       return switch (accountType){
            case SAVING -> new SavingAccount(currency);
            case STANDARD -> new StandardAccount(currency);
            case PREMIUM -> new PremiumAccount(currency);
        };
    }
}
