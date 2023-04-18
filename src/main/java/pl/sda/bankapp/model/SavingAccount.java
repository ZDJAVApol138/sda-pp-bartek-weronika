package pl.sda.bankapp.model;

import lombok.Getter;
import lombok.Setter;
import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;

import java.math.BigDecimal;

public class SavingAccount extends Account {

    private static final int MONTHLY_FEE = 3;



    public SavingAccount(long id, long customerId, Currency currency) {
        super(id, customerId, currency, AccountType.SAVING);
    }

    @Override
    public void chargeAccount() {
        setCurrentAmount(getCurrentAmount().subtract(BigDecimal.valueOf(MONTHLY_FEE)));
    }

    // TODO: Scheduling
    public void addInterest() {
        BigDecimal interest = calculateInterest();
        BigDecimal total = getCurrentAmount().add(interest);
        setCurrentAmount(total);
    }

    private BigDecimal calculateInterest() {
        return getCurrentAmount().multiply(BigDecimal.valueOf(0.08));
    }

}