package pl.sda.bankapp.model;

import java.util.ArrayList;
import java.util.Optional;

public class Bank {

    private final String name;
    private final ArrayList<Customer> customers;

    public Bank(String name, ArrayList<Customer> customers) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    public boolean deleteCustomer(String pesel) {
        Customer customer = findCustomer(pesel);
        return customers.remove(customer);
    }

    public Customer findCustomer(String pesel) {
        return customers.stream()
                .filter(c -> c.getPesel().equals(pesel))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found."));
    }

    public void listCustomer() {
        for (Customer customer : customers
        ) {
            System.out.println(customer.toString());
        }
    }
}
