package main.project.service;

public interface ValidationService {
	boolean isValidUsername(String userName);

	boolean isValidPassword(String password);
}
