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

	// TODO Build in all steps of controller

	// TODO step 1: deal with input from terminal and make decision based off of
	// that
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean workingUser = true;

		System.out.println("-----Starting Cryptographic System-----");
		while (workingUser) {
			System.out.println("Do you want to encrypt or decrypt or stop?");
			String input = reader.readLine();
			
			if(input.equals("stop")) {
				workingUser = false;
				break;
			}

			if (input.equals("encrypt")) {
				System.out.println("Please type in the location of your document");
				String TextDocName = reader.readLine();
				String TextDoc = getFileText(TextDocName);
				System.out.println("encrypting file...");
				String cypherDoc = encrypt(TextDoc);
				writeDoc(cypherDoc, TextDocName);
				System.out.println("Document has been encrypted");

			} else if (input.equals("decrypt")) {
				System.out.println("Please type in the location of your document");
				String TextDocName = reader.readLine();
				String TextDoc = getFileText(TextDocName);
				System.out.println("decrypting file...");
				String cypherDoc = decrypt(TextDoc);
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
	private static String encrypt(String plainText) {
		String cipherText = "";
		KeyManager keyManager = new KeyManager(); //Creating keyManager Object, then generating key
		keyManager.genorateKey();
		boolean generatedKey = keyManager.expandKey();
		System.out.println("Key Generated: "+ generatedKey);
		
		for(int i = 0; i < 1; i++) {
		CipherText cipherBuilder = new CipherText("encrypt me!00000"); //can only use 16byte increments of text
		cipherBuilder.subBytes(); //make sure returns arent needed (returns should be void or boolean)
		cipherBuilder.shiftRows();
		cipherBuilder.mixColumns();
		cipherBuilder.encryptKey(keyManager.getRoundKey(i));
		String cipherTextFragment = cipherBuilder.getCipherText(); 
		cipherText = cipherText.concat(cipherTextFragment);
		}
		
		return cipherText;
	}

	// TODO step 3: break up text to 128 bit lengths
	// Loop decryption to decrypt entire document
	// return plain text
	private static String decrypt(String cipherText) {
		System.out.println("This is the ciphertext: " + cipherText);
		return "decrypted Text";
	}
	
	private String[] breakInto16Bytes(String file) {
		int padding = 16 - (file.length()%16);
		for(int i = 0; i < padding; i++)//add padding to end of text to make text divisible by 16
			file = file.concat("0");
		String[] textChunks = new String[file.length()/16];
		for(int i = 0; i < textChunks.length; i++) {
			int start = (i+1)*16-16;
			int end = (i+1)*16;
			file.getChars(start, end, textChunks, i);
		}
		return null;
		
	}

	private static String getFileText(String file) {
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

	private static void writeDoc(String doc, String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			System.out.println(fileName + " " + doc);
			writer.write(doc);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
