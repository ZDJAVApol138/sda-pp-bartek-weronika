package pl.sda.bankapp.model;

import java.math.BigDecimal;

public class SavingAccount extends Account{
    BigDecimal monthlyChargeRate = BigDecimal.valueOf(3);

    private void chargeAccount(BigDecimal monthlyChargeRate) {
        getCurrentAmount().subtract(monthlyChargeRate);
    }
}
