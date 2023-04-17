package pl.sda.bankapp.model;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;

import java.math.BigDecimal;

public class StandardAccount extends Account{

   public StandardAccount(long id, long customerId, String accountNumber, Currency currency, BigDecimal currentAmount) {
       super(id, customerId, accountNumber, AccountType.STANDARD, currency, currentAmount);
   }

    BigDecimal monthlyChargeRate = BigDecimal.valueOf(5);

    private void chargeAccount(BigDecimal monthlyChargeRate) {
        getCurrentAmount().subtract(monthlyChargeRate);
    }
}
