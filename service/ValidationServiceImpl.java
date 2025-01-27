package main.project.service;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validateUserName(String username) {
		if (username.matches("^[A-Z]\\w{2,}$"))
			return true;
		if (username.trim().isEmpty()) {
			System.out.println("Invalid Username: Username must not be empty");
			return false;
		}
		if (username.trim().length() != username.length())
			System.out.println("Invalid Username: Username must not starts or ends with spaces");
		username = username.trim();
		if (username.length() < 3)
			System.out.println("Invalid Username: Username must be at least 3 characters");
		if (!Character.isUpperCase(username.charAt(0)))
			System.out.println("Invalid Username: Username must start with an upper case letter");
		return false;
	}

	@Override
	public boolean validatePassword(String password) {
		if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.!@#$%^&*_()-]).{6,}$"))
			return true;
		if (password.trim().isEmpty()) {
			System.out.println("Invalid Password: Password must not be empty");
			return false;
		}
		if (password.trim().length() != password.length())
			System.out.println("Invalid Password: Password must not contain with spaces");
		password = password.trim();
		if (password.length() < 6)
			System.out.println("Invalid Password: Password must be at least 6 characters");
		if (!password.matches(".*[a-z].*"))
			System.out.println("Invalid Password: Password must contain a lower case letter");
		if (!password.matches(".*[0-9].*"))
			System.out.println("Invalid Password: Password must contain a digit");
		if (!password.matches(".*[A-Z].*"))
			System.out.println("Invalid Password: Password must contain an upper case letter");
		if (!password.matches(".*[.!@#$%^&*_].*"))
			System.out.println("Invalid Password: Password must contain a special character");
		return false;
	}
}
