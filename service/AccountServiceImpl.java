package main.project.service;

import main.project.model.*;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

	private final EWallet eWallet = EWallet.getInstance();

	@Override
	public void createAccount(AccountDH account) {
		if (eWallet.getAccounts().stream().noneMatch(l -> l.getUserName().equals(account.getUserName()))) {
			eWallet.getAccounts().add(new Account(account.getUserName(), account.getPassword()));
			System.out.println("Account created successfully");
			return;
		}
		System.out.println("Account with username (" + account.getUserName() + ") already exists");
	}

	@Override
	public boolean loginAccount(AccountDH account) {
		Optional<Account> b = fetchAccount(account.getUserName());
		if (b.isEmpty()) {
			System.out.println("Account with username (" + account.getUserName() + ") does not exist");
			return false;
		}
		else if (!b.get().getPassword().equals(account.getPassword())) {
			System.out.println("Incorrect password");
			return false;
		}
		System.out.println("Login Success");
		return true;
	}

	@Override
	public void deposit(AccountDH accountDH) {
		if (!isValidAndActiveUserName(accountDH.getUserName()))
			return;
		Account account = fetchAccount(accountDH.getUserName()).get();
		account.setBalance(account.getBalance() + accountDH.getValueToDeposit());
	}

	@Override
	public void withdraw(AccountDH accountDH) {
		if (!isValidAndActiveUserName(accountDH.getUserName()))
			return;
		Account account = fetchAccount(accountDH.getUserName()).get();
		if (account.getBalance() < accountDH.getValueToWithdraw()) {
			System.out.println("Insufficient balance");
			return;
		}
		account.setBalance(account.getBalance() - accountDH.getValueToWithdraw());
	}

	@Override
	public boolean isValidAndActiveUserName(String username) {
		Optional<Account> optionalAccount = fetchAccount(username);
		if (optionalAccount.isEmpty()) {
			System.out.println("Account with username (" + username + ") does not exist");
			return false;
		}
		else if (!optionalAccount.get().getActive()) {
			System.out.println("Account with username (" + username + ") is not active");
			return false;
		}
		return true;
	}

	private Optional<Account> fetchAccount(String username) {
		return eWallet.getAccounts().stream().filter(l -> l.getUserName().equals(username)).findFirst();
	}

	@Override
	public void showBalance(AccountDH accountDH) {
		if (!isValidAndActiveUserName(accountDH.getUserName()))
			return;
		Account account = fetchAccount(accountDH.getUserName()).get();
		System.out.println("User Balance:");
		System.out.println("Username: " + account.getUserName());
		System.out.println("Balance: " + account.getBalance());
	}

	// Transfer Account depositAccount, Account withdrawAccount, int money
	// TODO without duplication
	// TODO make Transfer
	// TODO create function with name transfer that return
	// TODO true if transfer success
	// TODO false if transfer fail
	// TODO check if depositAccount and withdrawAccount exist on wallet or not if not print account not exist
	// TODO check if depositAccount and withdrawAccount is active or not  if not print account not active
	// TODO check if withdrawAccount balance is greater than money if not print can't deposit because ....
}
