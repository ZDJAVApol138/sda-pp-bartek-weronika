package pl.sda.bankapp;

import pl.sda.bankapp.model.Address;

public class Main {

    public static void main(String[] args) {
//hashcode oraz equals
        Address address1 = new Address("Amsterdam","Amsterdamska","00-001");
        Address address2 = new Address("Barcelona","Barcelońska","00-002");
        System.out.println(address1.toString());


    }
}
