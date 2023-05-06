package pl.sda.bankapp.dao;

import lombok.RequiredArgsConstructor;
import pl.sda.bankapp.mapper.CSVMapper;
import pl.sda.bankapp.model.Account;
import pl.sda.bankapp.model.Address;
import pl.sda.bankapp.model.Customer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomersDAO {

    public static final Path CUSTOMERS_CSV_FILE = Path.of("./customers.csv");

    private final AccountsDAO accountsDAO;

    void saveAll(List<Customer> customers) {
        List<String> customersRows = new ArrayList<>();
        customersRows.add(CSVMapper.CUSTOMERS_HEADER);

        Map<String, List<Account>> accountsMap = new HashMap<>();
        customers.stream()
                .peek(customer -> accountsMap.put(customer.getId().toString(), customer.getAccounts()))
                .map(Customer::toCsv)
                .forEach(customersRows::add);

        accountsDAO.saveAll(accountsMap);

        try {

            Files.write(
                    CUSTOMERS_CSV_FILE,
                    customersRows,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING

            );
        } catch (IOException e) {
            System.err.printf("IOException: %s", e.getMessage());
        }
    }

    List<Customer> fetchAll() {
        try {
            Map<String, List<Account>> accountsMap = accountsDAO.fetchAll();
            return Files.lines(CUSTOMERS_CSV_FILE).skip(1)
                    .map(line -> line.split(CSVMapper.DELIMITER))
                    .map(columns -> createCustomer(columns, accountsMap))
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            System.err.printf("IOException: %s%n", ex.getMessage());
        }
        return new ArrayList<>();
    }

    private Customer createCustomer(String[] columns, Map<String, List<Account>> accountsMap) {
        String customerID = columns[CSVMapper.CustomerColumns.ID];

        UUID id = UUID.fromString(customerID);
        String name = columns[CSVMapper.CustomerColumns.NAME];
        String surname = columns[CSVMapper.CustomerColumns.SURNAME];
        String pesel = columns[CSVMapper.CustomerColumns.PESEL];
        int age = Integer.parseInt(columns[CSVMapper.CustomerColumns.AGE]);
        String email = columns[CSVMapper.CustomerColumns.EMAIL];
        String phone = columns[CSVMapper.CustomerColumns.PHONE];
        LocalDate dateOfBirth = LocalDate.parse(columns[CSVMapper.CustomerColumns.DATE_OF_BIRTH]);
        String city = columns[CSVMapper.CustomerColumns.CITY];
        String street = columns[CSVMapper.CustomerColumns.STREET];
        String postCode = columns[CSVMapper.CustomerColumns.POST_CODE];

        List<Account> accounts = accountsMap.getOrDefault(customerID, new ArrayList<>());

        return Customer.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .pesel(pesel)
                .phone(phone)
                .age(age)
                .email(email)
                .dateOfBirth(dateOfBirth)
                .address(new Address(city, street, postCode))
                .accounts(accounts)
                .build();
    }

}