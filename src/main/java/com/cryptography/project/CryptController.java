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

			System.out.println("Please type in the location of your document");
			String TextDocName = reader.readLine();
			String TextDoc = getFileText(TextDocName);

			if (input.equals("encrypt")) {
				System.out.println("encrypting file...");
				String cypherDoc = encrypt(TextDoc);
				writeDoc(cypherDoc, TextDocName);
				System.out.println("Document has been encrypted");

			} else if (input.equals("decrypt")) {
				System.out.println("decrypting file...");
				String cypherDoc = decrypt(TextDoc);
				writeDoc(cypherDoc, TextDocName);
				System.out.println("Document has been encrypted");

			}else if(input.equals("stop")) {
				workingUser = false;
				
			} else {
				System.out.println("Unexpected input, please try again"); // TODO make statement loop
			}
		}
	}

	// TODO step 2: break up text to 128 bit lengths
	// loop encryption to encrypt entire document
	// return encrypted document
	private static String encrypt(String plainText) {

		return "encrypted text";
	}

	// TODO step 3: break up text to 128 bit lengths
	// Loop decryption to decrypt entire document
	// return plain text
	private static String decrypt(String cipherText) {

		return "decrypted Text";
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writer.write(doc);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
