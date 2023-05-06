package pl.sda.bankapp.dao;
import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.mapper.CSVMapper;
import pl.sda.bankapp.model.Account;
import pl.sda.bankapp.model.PremiumAccount;
import pl.sda.bankapp.model.SavingAccount;
import pl.sda.bankapp.model.StandardAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class AccountsDAO {

    public static final Path ACCOUNTS_CSV_FILE = Path.of("./accounts.csv");

    void saveAll(Map<String, List<Account>> accountsMap) {
        List<String> accountsRows = new ArrayList<>();
        accountsRows.add(CSVMapper.ACCOUNTS_HEADER);

        accountsMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(account -> account.toCsv(entry.getKey())))
                .forEach(accountsRows::add);

        try {
            Files.write(
                    ACCOUNTS_CSV_FILE,
                    accountsRows,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING

            );
        } catch (IOException e) {
            System.err.printf("IOException: %s", e.getMessage());
        }

    }


    Map<String, List<Account>> fetchAll() {

        Map<String, List<Account>> accountsMap = new HashMap<>();
        try {
            Files.lines(ACCOUNTS_CSV_FILE).skip(1)
                    .map(line -> line.split(CSVMapper.DELIMITER))
                    .forEach(columns -> {

                        Account account = createAccount(columns);
                        List<Account> accountsList = new ArrayList<>();
                        accountsList.add(account);

                        String customerId = columns[CSVMapper.AccountColumns.CUSTOMER_ID];
                        accountsMap.merge(customerId, accountsList, (accounts, accounts2) -> {
                            accounts.addAll(accounts2);
                            return accounts;
                        });
                    });
        } catch (IOException ex) {
            System.err.printf("IOException: %s", ex.getMessage());
        }
        return accountsMap;
    }

    private Account createAccount(String[] columns) {
        UUID id = UUID.fromString(columns[CSVMapper.AccountColumns.ID]);
        String accountNumber = columns[CSVMapper.AccountColumns.ACCOUNT_NUMBER];
        AccountType accountType = AccountType.valueOf(columns[CSVMapper.AccountColumns.ACCOUNT_TYPE]);
        Currency currency = Currency.valueOf(columns[CSVMapper.AccountColumns.CURRENCY]);
        BigDecimal currentAmount = new BigDecimal(columns[CSVMapper.AccountColumns.CURRENT_AMOUNT]);

        Account account = switch (accountType) {
            case SAVING -> new SavingAccount();
            case STANDARD -> new StandardAccount();
            case PREMIUM -> new PremiumAccount();
        };

        account.setId(id);
        account.setAccountNumber(accountNumber);
        account.setAccountType(accountType);
        account.setCurrentAmount(currentAmount);
        account.setCurrency(currency);
        return account;
    }


}
