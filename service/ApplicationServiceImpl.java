package main.project.service;

import main.project.model.Account;

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
		boolean isExceedTheLimitOrUserRequestClose;
		do {
			isExceedTheLimitOrUserRequestClose = showWelcomeCommands();
		}
		while (!isExceedTheLimitOrUserRequestClose);
	}

	private boolean showWelcomeCommands() {
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
				return false;
			case 'B':
			case 'b':
				signup();
				return false;
			case 'C':
			case 'c':
				System.out.println("you are welcome.");
				return true;
			default:
				System.out.println("Invalid Choose");
			}
			count++;
		}
		System.out.println("Sorry, You exceed the limit.\nPlease try again later.\nBye.");
		return true;
	}

	private void signup() {
		String username = acceptAValidField("username", validationService::isValidUsername);
		String password = acceptAValidField("password", validationService::isValidPassword);
		Account account = new Account(username, password);
		accountService.createAccount(account);
	}

	private void login() {
		String username = acceptAValidField("username", validationService::isValidUsername);
		String password = acceptAValidField("password", validationService::isValidPassword);
		// 8.TODO SERVICE OF ACCOUNT TO LOGIN
		if (accountService.loginAccount(new Account(username, password))) {
			System.out.println("Login Success");
			services();
		}
		else {
			System.out.println("Account not Exist");
		}
	}

	private String acceptAValidField(String fieldName, Predicate<String> validator) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username");
		String name = scanner.nextLine();
		while (!validator.test(name)) {
			System.out.println("Please enter a valid " + fieldName);
			name = scanner.nextLine();
		}
		return name;
	}

	private void services() {
		System.out.println("1.Deposit   2.Withdraw    3.show details    4.Transfer    5. show balance   6.exit  7.logout");
		// TODO create switch case such as on run function
		// TODO every case on switch call function  don't forget (Invalid choose)
	}

	// TODO create deposit function
	void deposit(Account a) {
		// input int money
		Scanner scanner = new Scanner(System.in);
		double mo = scanner.nextDouble();
		// TODO pls validate money >= 100 and <= 20000
	}

	// TODO create Withdraw function
	void withdraw(Account a) {
		// input int money
		// TODO pls validate money >= 100 and <= 8000
	}

	void showDetails(Account a) {
	}

	void transfer(Account withdrawAccount) {
		// TODO USER MUST give me user name of account that will transfer
		// TODO input Account depositAccount
		// TODO input int money
	}

	void showBalance(Account a) {
	}
}
