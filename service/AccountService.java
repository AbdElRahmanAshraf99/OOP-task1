package main.project.service;

import main.project.model.Account;

public interface AccountService {

    void createAccount(Account account);
    boolean loginAccount(Account account);
}
