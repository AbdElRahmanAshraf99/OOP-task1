package main.project;

import main.project.service.*;

public class Main {
	public static void main(String[] args) {
		AccountService accountService = new AccountServiceImpl();
		ValidationService validationService = new ValidationServiceImpl();
		ApplicationServiceImpl applicationService = new ApplicationServiceImpl(accountService, validationService);
		applicationService.run();
	}
}
