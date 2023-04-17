

package pl.sda.bankapp.model;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;

import java.math.BigDecimal;

public class PremiumAccount extends Account {

    private static final int MONTHLY_FEE = 10;

    public PremiumAccount(long id, long customerId, Currency currency) {
        super(id, customerId, currency, AccountType.PREMIUM);
    }

    @Override
    public void chargeAccount() {
        setCurrentAmount(getCurrentAmount().subtract(BigDecimal.valueOf(MONTHLY_FEE)));
    }
}
