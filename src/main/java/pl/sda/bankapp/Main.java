package pl.sda.bankapp;

import pl.sda.bankapp.model.Address;
import pl.sda.bankapp.model.Customer;

public class Main {

    public static void main(String[] args) {
//hashcode oraz equals
        Address address1 = new Address("Amsterdam","Amsterdamska","00-001");
        Address address2 = new Address("Barcelona","Barcelońska","00-002");
        System.out.println(address1.toString());

Customer customer1 = new Customer("Alicja","Artemska","1111111111","aa@aa.aa",1,11,address1,"111111");

        System.out.println(customer1.toString());
    }
}
