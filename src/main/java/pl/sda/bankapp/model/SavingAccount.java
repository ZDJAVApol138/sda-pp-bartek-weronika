package pl.sda.bankapp.model;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;

import java.math.BigDecimal;

public class SavingAccount extends Account {

    public SavingAccount(long id, long customerId, String accountNumber, Currency currency, BigDecimal currentAmount) {
        super(id, customerId, accountNumber, AccountType.SAVING, currency, currentAmount);
    }

    BigDecimal monthlyChargeRate = BigDecimal.valueOf(3);

    @Override
    public void chargeAccount(BigDecimal monthlyChargeRate) {
        setCurrentAmount(getCurrentAmount().subtract(monthlyChargeRate));
    }
}
