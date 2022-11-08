//Authors Aidan Scott Hamza Zaher and Nikitas Valtadoros
//Created to mimic the AES cryopographic algorithm

package com.cryptography.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CryptController {
	static int rounds = 9;
	//test key: lsoeifubnnenq0o
	//test directory: C:\Users\aidan\eclipse-workspace_java\com.cryptography.project\src\main\resources\Secrets.txt
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
			System.out.println("Do you want to 'encrypt', 'stop', or read the 'manual'?");
			String input = reader.readLine();

			if (input.equals("stop")) {
				workingUser = false;
				break;
			}

			if (input.equals("encrypt")) {
				System.out.println("Please type in the location of your document");
				String TextDocName = reader.readLine();
				String TextDoc = getFileText(TextDocName);
				System.out.println("Please type in 16 byte key");
				String seedKey = reader.readLine();
				if(seedKey.length() != 16) 
					seedKey = "sidlvnsieolsmgnh";
				System.out.println("encrypting file...");
				String cypherDoc = encrypt(TextDoc, seedKey);
				System.out.println("Encryption Complete");
				System.out.println("This is the encrypted text: " + cypherDoc);
				System.out.println("Cipher Text has been saved to memory, press enter to continue.");
				reader.readLine();
				System.out.println("decrypting file...");
				String plainText = decrypt(cypherDoc, seedKey);
				writeDoc(plainText, TextDocName);
				System.out.println("Decryption complete, check document for results");

			} else if (input.equals("manual")) {
				String manual = getFileText("src\\main\\resources\\manual.txt");
				String[] lines = manual.split("%");
				for(String i : lines)
					System.out.println(i);
				
			}else {
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

		for (String fragment : stringPackets) {
			CipherText cipherBuilder = new CipherText(fragment);
			// encryption section
			encryptFirstStep(cipherBuilder, keyManager, 0);
			encryptSecondStep(cipherBuilder, keyManager, 1);
			encryptSecondStep(cipherBuilder, keyManager, 2);
			encryptSecondStep(cipherBuilder, keyManager, 3);
			encryptSecondStep(cipherBuilder, keyManager, 4);
			encryptSecondStep(cipherBuilder, keyManager, 5);
			encryptSecondStep(cipherBuilder, keyManager, 6);
			encryptSecondStep(cipherBuilder, keyManager, 7);
			encryptSecondStep(cipherBuilder, keyManager, 8);
			encryptThirdStep(cipherBuilder, keyManager, 9);
			cipherText = cipherText.concat(cipherBuilder.getWorkingText());
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

		for (String fragment : stringPackets) {
			CipherText cipherBuilder = new CipherText(fragment);
			// decryption section
			//All steps and in order (there was an error when trying to use loops for secondStep)
			decryptFirstStep(cipherBuilder, keyManager, 9);
			decryptSecondStep(cipherBuilder, keyManager, 8);
			decryptSecondStep(cipherBuilder, keyManager, 7);
			decryptSecondStep(cipherBuilder, keyManager, 6);
			decryptSecondStep(cipherBuilder, keyManager, 5);
			decryptSecondStep(cipherBuilder, keyManager, 4);
			decryptSecondStep(cipherBuilder, keyManager, 3);
			decryptSecondStep(cipherBuilder, keyManager, 2);
			decryptSecondStep(cipherBuilder, keyManager, 1);
			decryptThirdStep(cipherBuilder, keyManager, 0);
			plainText = plainText.concat(cipherBuilder.getWorkingText());
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
		int padding = 16 -(file.length() % 16);
		for (int i = 0; i < padding; i++)// add padding to end of text to make text divisible by 16
			file = file.concat("0");
		//String[] textChunks = file.split("(?<=\\G................)"); //the previous solution, but didnt work with ciphertext
		String[] textChunks = new String[file.length()/16];
		
		textChunks[0] = file.substring(0, 16);
		for(int i = 1; i < textChunks.length; i++) {
			int start = i*16;
		    textChunks[i] = file.substring(start, start + 16);
		}
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
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			writer.write(doc);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
