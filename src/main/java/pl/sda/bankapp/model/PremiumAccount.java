package pl.sda.bankapp.model;

import java.math.BigDecimal;

public class PremiumAccount extends Account {
    BigDecimal monthlyChargeRate = BigDecimal.valueOf(10);

    private void chargeAccount(BigDecimal monthlyChargeRate) {
        getCurrentAmount().subtract(monthlyChargeRate);
    }
}
