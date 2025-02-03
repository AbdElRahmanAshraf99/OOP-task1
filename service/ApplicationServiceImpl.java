package main.project.service;

import main.project.model.AccountDH;

import java.util.Scanner;
import java.util.function.Predicate;

public class ApplicationServiceImpl implements ApplicationService {

	private final AccountService accountService;
	private final ValidationService validationService;

	public ApplicationServiceImpl(AccountService accountService, ValidationService validationService) {
		this.accountService = accountService;
		this.validationService = validationService;
	}

	@Override
	public void run() {
		System.out.println("Welcome Sir");
		showWelcomeCommands();
	}

	private void showWelcomeCommands() {
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		while (count < 4) {
			System.out.println("Please Enter your choose");
			System.out.println("a.login     b.signup   c.exit");
			char choose = scanner.next().charAt(0);
			switch (choose) {
			case 'A':
			case 'a':
				login();
				return;
			case 'B':
			case 'b':
				signup();
				count = 0;
				continue;
			case 'C':
			case 'c':
				System.out.println("you are welcome.");
				return;
			default:
				System.out.println("Invalid Choice");
			}
			count++;
		}
		System.out.println("Sorry, You exceed the limit.\nPlease try again later.\nBye.");
	}

	private void signup() {
		String username = acceptAValidField("username", validationService::isValidUsername);
		String password = acceptAValidField("password", validationService::isValidPassword);
		AccountDH account = new AccountDH(username, password);
		accountService.createAccount(account);
	}

	private void login() {
		String username = acceptAValidField("username", validationService::isValidUsername);
		String password = acceptAValidField("password", validationService::isValidPassword);
		AccountDH account = new AccountDH(username, password);
		if (accountService.loginAccount(account))
			listServices(account);
	}

	private String acceptAValidField(String fieldName, Predicate<String> validator) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your " + fieldName);
		String name = scanner.nextLine();
		while (!validator.test(name)) {
			System.out.println("Please enter a valid " + fieldName);
			name = scanner.nextLine();
		}
		return name;
	}

	private void listServices(AccountDH account) {
		System.out.println("1.Deposit   2.Withdraw   3.Transfer   4.Show balance  5.exit  6.logout");
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		while (count < 4) {
			System.out.println("Please Enter your choose");
			int n = scanner.nextInt();
			switch (n) {
			case 1:
				deposit(account);
				listServices(account);
				return;
			case 2:
				withdraw(account);
				listServices(account);
				return;
			case 3:
				transfer(account);
				listServices(account);
				return;
			case 4:
				showBalance(account);
				listServices(account);
				return;
			case 5:
				return;
			case 6:
				showWelcomeCommands();
				return;
			default:
				System.out.println("Invalid Choice");
			}
			count++;
		}
		System.out.println("Sorry, You exceed the limit.\nPlease try again later.\nBye.");
	}

	private void deposit(AccountDH account) {
		System.out.println("Enter deposit amount (min 100, max 20000)");
		Scanner scanner = new Scanner(System.in);
		double money = scanner.nextDouble();
		while (money < 100 || money > 20000) {
			System.out.println("Please enter a valid deposit amount (min 100, max 20000)");
			money = scanner.nextDouble();
		}
		account.setValueToDeposit(money);
		accountService.deposit(account);
	}

	private void withdraw(AccountDH account) {
		System.out.println("Enter withdrawal amount (min 100, max 8000)");
		Scanner scanner = new Scanner(System.in);
		double money = scanner.nextDouble();
		while (money < 100 || money > 8000) {
			System.out.println("Please enter a valid withdrawal amount (min 100, max 8000)");
			money = scanner.nextDouble();
		}
		account.setValueToWithdraw(money);
		accountService.withdraw(account);
	}

	void transfer(AccountDH fromAccount) {
		// TODO USER MUST give me user name of account that will transfer
		// TODO input Account depositAccount
		// TODO input int money
	}

	void showBalance(AccountDH accountDH) {
		accountService.showBalance(accountDH);
	}
}
