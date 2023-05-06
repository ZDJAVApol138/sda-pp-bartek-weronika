package pl.sda.bankapp.model;

import lombok.*;
import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.mapper.CSVMapper;
import pl.sda.bankapp.utils.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {

    private UUID id = UUID.randomUUID();
    private Currency currency;
    private AccountType accountType;
    private BigDecimal currentAmount = BigDecimal.ZERO;
    private String accountNumber = AccountNumberGenerator.generate();


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

    public String toCsv(String customerId) {
        return String.join(CSVMapper.DELIMITER, List.of(
                        id.toString(), accountNumber, currency.name(), accountType.name(),
                        currentAmount.toString(), customerId
                )
        );
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
