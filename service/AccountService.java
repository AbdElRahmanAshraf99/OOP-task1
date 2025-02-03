package main.project.service;

import main.project.model.AccountDH;

public interface AccountService {

    void createAccount(AccountDH account);

    boolean loginAccount(AccountDH account);

    void deposit(AccountDH account);

    void withdraw(AccountDH account);

    boolean isValidAndActiveUserName(String username);

    void showBalance(AccountDH accountDH);
}
