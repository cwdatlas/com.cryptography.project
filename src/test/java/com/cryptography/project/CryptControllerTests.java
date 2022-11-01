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
	@Test
	public void writeDocTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String answer = "Hello World its me";
		com.cryptography.project.CryptController.writeDoc(answer, file);
		String writenText = com.cryptography.project.CryptController.getFileText(file);
		assertEquals(answer, writenText);
	}
	@Test
	public void breakInto16BytesTest() {
		String input = "Hello my name is Fatherlumberjack, what is yours";
		String answer = "Hello my name is";
		String[] byteArray = com.cryptography.project.CryptController.breakInto16Bytes(input);
		assertEquals(answer, byteArray[0]);//TODO make sure every section of the array == 16
	}
	@Test
	public void encryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		String fileData = com.cryptography.project.CryptController.getFileText(file);
		com.cryptography.project.CryptController.encrypt(fileData, key);
	}
	@Test
	public void decryptTest() {
		String file = "C:\\Users\\aidan\\eclipse-workspace_java\\com.cryptography.project\\src\\main\\resources\\Secrets.txt";
		String key = "Pathformwlsdibr5";
		String fileData = com.cryptography.project.CryptController.getFileText(file);
		com.cryptography.project.CryptController.decrypt(fileData, key);
	}
}
