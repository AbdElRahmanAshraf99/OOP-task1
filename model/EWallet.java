package main.project.model;

import java.util.*;

public class EWallet {
	private static EWallet instance;
	private final String name = "EraaSoft Cash";
	private final List<Account> accounts = new ArrayList<>();

	private EWallet() {
	}

	public static EWallet getInstance() {
		if (instance == null)
			instance = new EWallet();
		return instance;
	}

	public String getName() {
		return name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
}
