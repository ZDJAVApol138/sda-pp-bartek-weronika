package pl.sda.bankapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.sda.bankapp.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor  //@AllArgsConstructor
public class Bank {

    private final String name;
    @Setter
    private List<Customer> customers = new ArrayList<>();



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
                .orElseThrow(() -> new NotFoundException("Customer not found."));
    }

    public void listCustomers() {
        for (Customer customer : customers
        ) {
            System.out.println(customer.toString());
        }
    }
}
