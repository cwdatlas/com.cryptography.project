package com.cryptography.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.cryptography.project.CryptController;

public class CryptControllerTests {


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

	public void breakInto16BytesTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String text = com.cryptography.project.CryptController.getFileText(file);
		String[] byteArray = com.cryptography.project.CryptController.breakInto16Bytes(text);
		for (int i = 0; i < byteArray.length; i++) {
			assertEquals(byteArray[i].length(), 16);
		}
	}


	public void encryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		String fileData = com.cryptography.project.CryptController.getFileText(file);
		String CipherText = com.cryptography.project.CryptController.encrypt(fileData, key);
		assertNotNull(CipherText);
	}

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
		String cipherText = "";
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String fileOut = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\SecretsOut.txt";
		String text = com.cryptography.project.CryptController.getFileText(file);
		System.out.println("this is the length of the text" + text.toCharArray().length);
		String key = "Hatjformhlsd5br5";
		KeyManager keyManager = new KeyManager(); // Creating keyManager Object, then generating key
		keyManager.setKey(key);
		keyManager.expandKey();
		String[] byteArray1 = com.cryptography.project.CryptController.breakInto16Bytes(text);
		for (String fragment : byteArray1) {
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
			cipherText = cipherText.concat(cipherBuilder.getWorkingText());
			//Add concatonate then break appart again. if that works then save the string then read it back.
		}
		com.cryptography.project.CryptController.writeDoc(cipherText, fileOut); //Testing setting and getting string from file
		String cipherFile = com.cryptography.project.CryptController.getFileText(fileOut);
		String[] byteArray2 = com.cryptography.project.CryptController.breakInto16Bytes(cipherFile);
		for (String fragment : byteArray2) { 
			CipherText cipherBuilder = new CipherText(fragment);
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
		com.cryptography.project.CryptController.writeDoc(plainText, fileOut);
		//assertEquals(text, plainText);
	}
}
