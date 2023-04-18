package pl.sda.bankapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.utils.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Account {

    private long id;
    private long customerId;
    private Currency currency;
    private AccountType accountType;
    private BigDecimal currentAmount = BigDecimal.ZERO;
    private final String accountNumber = AccountNumberGenerator.generate();

    public Account() {
    }

    public Account(long id, long customerId, Currency currency, AccountType accountType) {
        this.id = id;
        this.customerId = customerId;
        this.currency = currency;
        this.accountType = accountType;
    }

    public abstract void chargeAccount();

    public void deposit(BigDecimal depositAmount) {
        currentAmount = currentAmount.add(depositAmount);

    }

    public void withdraw(BigDecimal withdrawAmount) {
        if (currentAmount.compareTo(withdrawAmount) >= 0) {
            currentAmount = currentAmount.subtract(withdrawAmount);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", accountNumber='" + accountNumber + '\'' +
                ", currency=" + currency +
                ", currentAmount=" + currentAmount +
                ", accountType=" + accountType +
                '}';
    }
}
