package com.andersen.spring.entity;

public class UserAccount {

    private int accountsNumber;

    private Double amount;

    private User user;

    private long id;

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

}
