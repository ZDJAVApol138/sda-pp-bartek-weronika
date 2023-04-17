package pl.sda.bankapp.model;

import pl.sda.bankapp.enums.AccountType;
import pl.sda.bankapp.enums.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account {

    private long id;
    private long customerId;
    private String accountNumber;
    private AccountType accountType;
    private Currency currency;
    private BigDecimal currentAmount = BigDecimal.ZERO;

    public Account() {

    }

    public Account(long id, long customerId, String accountNumber, AccountType accountType, Currency currency, BigDecimal currentAmount) {
        this.id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currency = currency;
        this.currentAmount = currentAmount;
    }

    private void deposit(BigDecimal depositValue) {
        currentAmount = currentAmount.add(depositValue);
    }

    private void withdraw(BigDecimal withdrawValue) {
        if (currentAmount.compareTo(withdrawValue) >= 0) {
            currentAmount = currentAmount.subtract(withdrawValue);
        }
    }

    public abstract void chargeAccount(BigDecimal chargeRate);

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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && customerId == account.customerId && accountNumber.equals(account.accountNumber) && accountType == account.accountType && currency == account.currency && currentAmount.equals(account.currentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, accountNumber, accountType, currency, currentAmount);
    }
}
