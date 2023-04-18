package pl.sda.bankapp.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Address {
    public String city;
    public String street;
    public String postCode;

    @Override
    public String toString(){
       // return " City: "+ city + " \n Street: " + street + " \n Postal code: " + postCode;
        //better practise
        return " City: %s \n Street: %s  \n Postal code %s".formatted(city,street,postCode);
    }

}
