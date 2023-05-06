package pl.sda.bankapp.dao;

import lombok.RequiredArgsConstructor;
import pl.sda.bankapp.model.Bank;
import pl.sda.bankapp.model.Customer;

import java.util.List;

@RequiredArgsConstructor
public class PersistenceContext {

    private final CustomersDAO customersDAO;

    public void persistData(Bank bank) {
        customersDAO.saveAll(bank.getCustomers());
    }

    public void loadData(Bank bank) {
        List<Customer> customers = customersDAO.fetchAll();
        bank.setCustomers(customers);
    }


}
