package com.andersen.spring.entity;

import com.andersen.spring.exceptions.InsufficientFunds;

public class UserAccount {

    private int accountsNumber;

    private Double amount;

    private User user;

    private long id;

    private long userId;

    public UserAccount() {

    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void replenishTheAccount(Double amount) {
        this.amount += amount;
    }

    public void reduceTheAccount(Double amount) throws InsufficientFunds {

        if (this.amount == null || this.amount < amount)
            throw new InsufficientFunds("На счету недостаточно денег.");
        else
            this.amount -= amount;
    }

    public int getAccountsNumber() {
        return accountsNumber;
    }

    public void setAccountsNumber(int accountsNumber) {
        this.accountsNumber = accountsNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }
}
