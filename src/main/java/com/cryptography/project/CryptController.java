//Authors Aidan Scott Hamza Zaher and Nikitas Valtadoros
//Created to mimic the AES cryopographic algorithm

package com.cryptography.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CryptController {
	static int rounds = 9;

	// TODO Build in all steps of controller

	// TODO step 1: deal with input from terminal and make decision based off of
	// that
	//C:\Users\aidan\eclipse-workspace_java\com.cryptography.project\src\main\resources\Secrets.txt
	//sldifmelcmvpquag
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean workingUser = true;

		System.out.println("-----Starting Cryptographic System-----");
		while (workingUser) {
			System.out.println("Do you want to encrypt or decrypt or stop?");
			String input = reader.readLine();

			if (input.equals("stop")) {
				workingUser = false;
				break;
			}

			if (input.equals("encrypt")) {
				System.out.println("Please type in the location of your document");
				String TextDocName = reader.readLine();
				String TextDoc = getFileText(TextDocName);
				System.out.println("Please type in 16byte key");
				String seedKey = reader.readLine();
				System.out.println("encrypting file...");
				String cypherDoc = encrypt(TextDoc, seedKey);
				writeDoc(cypherDoc, TextDocName);
				System.out.println("Document has been encrypted");

			} else if (input.equals("decrypt")) {
				System.out.println("Please type in the location of your document");
				String TextDocName = reader.readLine();
				String TextDoc = getFileText(TextDocName);
				System.out.println("Please type in 16byte key");
				String seedKey = reader.readLine();
				System.out.println("decrypting file...");
				String cypherDoc = decrypt(TextDoc, seedKey);
				writeDoc(cypherDoc, TextDocName);
				System.out.println("Document has been encrypted");

			} else {
				System.out.println("Unexpected input, please try again"); // TODO make statement loop
			}
		}
	}

	// TODO step 2: break up text to 128 bit lengths
	// loop encryption to encrypt entire document
	// return encrypted document
	//private
	public static String encrypt(String plainText, String seedKey) {
		String cipherText = "";
		KeyManager keyManager = new KeyManager(); // Creating keyManager Object, then generating key
		keyManager.setKey(seedKey);
		keyManager.expandKey();
		String[] stringPackets = breakInto16Bytes(plainText);

		for (int i = 0; i < stringPackets.length; i++) {// Round 1
			CipherText cipherBuilder = new CipherText(stringPackets[i]); // can only use 16byte increments of text
			encryptFirstStep(cipherBuilder, keyManager, 0);

			for (int e = 1; e < (rounds - 1); e++) {// rounds 2 - n-1
				encryptSecondStep(cipherBuilder, keyManager, e + 1);
			}
			encryptThirdStep(cipherBuilder, keyManager, rounds-1);
			String cipherTextFragment = cipherBuilder.getWorkingText();
			cipherText = cipherText.concat(cipherTextFragment);
		}
		return cipherText;
	}

	// TODO step 3: break up text to 128 bit lengths
	// Loop decryption to decrypt entire document
	// return plain text
	public static String decrypt(String cipherText, String seedKey) {
		String plainText = "";
		KeyManager keyManager = new KeyManager(); // Creating keyManager Object, then generating key
		keyManager.setKey(seedKey);
		keyManager.expandKey();
		String[] stringPackets = breakInto16Bytes(cipherText);

		for (int i = 0; i < stringPackets.length; i++) {
			CipherText cipherBuilder = new CipherText(stringPackets[i]); // can only use 16byte increments of text
			decryptFirstStep(cipherBuilder, keyManager, rounds - 1);
			for (int e = 1; e < rounds - 1; e++) {// rounds 2 - n-1
				decryptSecondStep(cipherBuilder, keyManager, rounds - e - 1);
			}
			decryptThirdStep(cipherBuilder, keyManager, 0);
			String plainTextFragment = cipherBuilder.getWorkingText();// TODO name method more accurately (returns cipher
																		// and plain text)
			plainText = plainText.concat(plainTextFragment);
		}

		return plainText;
	}
	//encrypt steps
	public static void encryptFirstStep(CipherText cipherBuilder, KeyManager keyManager, int roundNumber) {
		cipherBuilder.encryptKey(keyManager.getRoundKey(roundNumber));
	}
	
	public static void encryptSecondStep(CipherText cipherBuilder, KeyManager keyManager, int roundNumber) {
		cipherBuilder.subBytes(); // make sure returns arent needed (returns should be void or boolean)
		cipherBuilder.shiftRows();
		//cipherBuilder.mixColumns();
		cipherBuilder.encryptKey(keyManager.getRoundKey(roundNumber));
	}
	
	public static void encryptThirdStep(CipherText cipherBuilder, KeyManager keyManager, int roundNumber) {
		cipherBuilder.subBytes(); // round n
		cipherBuilder.shiftRows();
		cipherBuilder.encryptKey(keyManager.getRoundKey(roundNumber)); // we dont want the same round key to be
	}
	//decrypt steps
	public static void decryptFirstStep(CipherText cipherBuilder, KeyManager keyManager, int roundNumber) {
		cipherBuilder.dectryptKey(keyManager.getRoundKey(roundNumber)); // needs to get key for round 9 (8)
		cipherBuilder.shiftRowsInverse();
		cipherBuilder.subBytesInverse();
	}
	
	public static void decryptSecondStep(CipherText cipherBuilder, KeyManager keyManager, int roundNumber) {
		cipherBuilder.dectryptKey(keyManager.getRoundKey(roundNumber));
		//cipherBuilder.mixColumnsInverse();
		cipherBuilder.shiftRowsInverse();
		cipherBuilder.subBytesInverse(); // make sure returns arent needed (returns should be void or boolean)
	}
	
	public static void decryptThirdStep(CipherText cipherBuilder, KeyManager keyManager, int roundNumber) {
		cipherBuilder.dectryptKey(keyManager.getRoundKey(roundNumber)); // we dont want the same round key to be generated
	}
	//private
	public static String[] breakInto16Bytes(String file) {
		int padding = 16 - (file.length() % 16);
		for (int i = 0; i < padding; i++)// add padding to end of text to make text divisible by 16
			file = file.concat("0");
		String[] textChunks = file.split("(?<=\\G................)");
		return textChunks;

	}
	//private
	public static String getFileText(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String plainText = "";
		String line = null;
		boolean loop = true;
		while (loop) {
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("File Not Found, or other issue occured regarding reading file");
			}
			if (line != null) {
				plainText = plainText + line;
			} else {
				loop = false;
			}
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return plainText;
	}
	//private
	public static void writeDoc(String doc, String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(doc);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
