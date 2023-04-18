

package pl.sda.bankapp.model;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public class PremiumAccount extends Account {

    private static final int MONTHLY_FEE = 10;

    public PremiumAccount(Currency currency) {
        super(currency, AccountType.PREMIUM);
    }

    @Override
    public void chargeAccount() {
        setCurrentAmount(getCurrentAmount().subtract(BigDecimal.valueOf(MONTHLY_FEE)));
    }
}
