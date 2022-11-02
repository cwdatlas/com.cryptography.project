package com.cryptography.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.cryptography.project.CryptController;

public class CryptControllerTests {

	@Test
	public void getFileTextTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String text = com.cryptography.project.CryptController.getFileText(file);
		assertNotNull(text);
	}

	public void writeDocTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String answer = "Hello World its me";
		com.cryptography.project.CryptController.writeDoc(answer, file);
		String writenText = com.cryptography.project.CryptController.getFileText(file);
		assertEquals(answer, writenText);
	}

	@Test
	public void breakInto16BytesTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String text = com.cryptography.project.CryptController.getFileText(file);
		String[] byteArray = com.cryptography.project.CryptController.breakInto16Bytes(text);
		for (int i = 0; i < byteArray.length; i++) {
			assertEquals(byteArray[i].length(), 16);
		}
	}

	@Test
	public void encryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		String fileData = com.cryptography.project.CryptController.getFileText(file);
		String CipherText = com.cryptography.project.CryptController.encrypt(fileData, key);
		assertNotNull(CipherText);
	}

	@Test
	public void decryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		String fileData = com.cryptography.project.CryptController.getFileText(file);
		String plainText = com.cryptography.project.CryptController.decrypt(fileData, key);
		assertNotNull(plainText);
	}

	@Test
	public void controllerTest() {
		String plainText = "";
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Gasby.txt";
		String text = com.cryptography.project.CryptController.getFileText(file);
		String key = "Hatjformhlsd5br5";
		KeyManager keyManager = new KeyManager(); // Creating keyManager Object, then generating key
		keyManager.setKey(key);
		keyManager.expandKey();
		String[] byteArray = com.cryptography.project.CryptController.breakInto16Bytes(text);
		for (String fragment : byteArray) {
			CipherText cipherBuilder = new CipherText(fragment);
			// encryption section
			com.cryptography.project.CryptController.encryptFirstStep(cipherBuilder, keyManager, 0);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 1);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 2);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 3);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 4);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 5);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 6);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 7);
			com.cryptography.project.CryptController.encryptSecondStep(cipherBuilder, keyManager, 8);
			com.cryptography.project.CryptController.encryptThirdStep(cipherBuilder, keyManager, 9);
			// decryption section
			com.cryptography.project.CryptController.decryptFirstStep(cipherBuilder, keyManager, 9);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 8);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 7);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 6);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 5);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 4);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 3);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 2);
			com.cryptography.project.CryptController.decryptSecondStep(cipherBuilder, keyManager, 1);
			com.cryptography.project.CryptController.decryptThirdStep(cipherBuilder, keyManager, 0);
			plainText = plainText.concat(cipherBuilder.getWorkingText());
		}
		assertEquals(text, plainText);
	}
}
