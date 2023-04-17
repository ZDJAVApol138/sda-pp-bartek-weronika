package pl.sda.bankapp.model;

import java.math.BigDecimal;

public class StandardAccount extends Account{
    BigDecimal monthlyChargeRate = BigDecimal.valueOf(5);

    private void chargeAccount(BigDecimal monthlyChargeRate) {
        getCurrentAmount().subtract(monthlyChargeRate);
    }
}
