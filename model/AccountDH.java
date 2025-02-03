package main.project.model;

public class AccountDH {
	private final String userName;
	private final String password;
	private double valueToWithdraw;
	private double valueToDeposit;

	public AccountDH(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.valueToWithdraw = 0;
		this.valueToDeposit = 0;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public double getValueToDeposit() {
		return valueToDeposit;
	}

	public double getValueToWithdraw() {
		return valueToWithdraw;
	}

	public void setValueToWithdraw(double valueToWithdraw) {
		this.valueToWithdraw = valueToWithdraw;
	}

	public void setValueToDeposit(double valueToDeposit) {
		this.valueToDeposit = valueToDeposit;
	}
}
