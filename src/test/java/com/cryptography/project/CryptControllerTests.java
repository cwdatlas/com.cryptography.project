package com.cryptography.project;

import org.junit.jupiter.api.Test;

import com.cryptography.project.CryptController;

public class CryptControllerTests {

	public void encryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		com.cryptography.project.CryptController.encrypt(file, key);
	}
	
	public void decryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		com.cryptography.project.CryptController.decrypt(file, key);
	}
}
