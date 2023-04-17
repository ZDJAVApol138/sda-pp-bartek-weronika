package pl.sda.bankapp.model;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;
import pl.sda.bankapp.utils.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.Objects;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                customerId == account.customerId &&
                Objects.equals(accountNumber, account.accountNumber) &&
                currency == account.currency &&
                Objects.equals(currentAmount, account.currentAmount) &&
                accountType == account.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, accountNumber, currency,
                currentAmount, accountType);
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
