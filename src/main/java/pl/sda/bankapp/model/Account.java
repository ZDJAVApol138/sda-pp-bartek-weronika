package pl.sda.bankapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.utils.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Account {

    private final UUID id = UUID.randomUUID();
    private Currency currency;
    private AccountType accountType;
    private BigDecimal currentAmount = BigDecimal.ZERO;
    private final String accountNumber = AccountNumberGenerator.generate();

    public Account() {
    }

    public Account(Currency currency, AccountType accountType) {
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
                ", accountNumber='" + accountNumber + '\'' +
                ", currency=" + currency +
                ", currentAmount=" + currentAmount +
                ", accountType=" + accountType +
                '}';
    }
}
