package com.cryptography.project;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.python.util.PythonInterpreter;

public class KeyManager implements KeyManagerI {
	private int[][] seedKey = new int[4][4];
	private int[][] ExpandedKey = new int[44][4]; // TODO set to actual size

	// Constructor, so if you need to initialize any variables do it here
	// Also, we are wanting to have our key and key variables to be in hex, so you
	// will have
	// to change the variable declarations to the hexNumber variable or whatever it
	// takes
	// to have a hex variable (add the library to dependencies in Pom file if you
	// need to
	public KeyManager() {

	}

	// TODO update so roundNumber is hashed and then turned to a hex and placed in
	// the array
	public String[][] getRoundKey(int roundNumber) {// TODO create section of key creation based on
		String[][] roundKey = new String[4][4];
		int position = roundNumber * 4;
		for (int i = 0; i < 4; i++) {
			for (int l = 0; l < 4; l++)
				roundKey[i][l] = Integer.toHexString(ExpandedKey[(i) + position][(l)]);
		}
		return roundKey;
	}

	public int[][] expandKey() {
		int[] storedKey;
		int[] xored = null;
		if (seedKey[0] == null) {
			System.out.println("you must set key with a 16 byte key. Can not run expand key without seedKey");
		} else {
			for (int i = 0; i < seedKey.length; i++)
				ExpandedKey[i] = seedKey[i];

			for (int i = 4; i < ExpandedKey.length; i++) {
				storedKey = ExpandedKey[i - 1];
				if (i % 4 == 0) {// TODO add sub-box to make line more variable
					storedKey = rotateKey(storedKey);
					xored = roundCon(i / 4);
				} else {
					xored = ExpandedKey[i - 4];
				}
				ExpandedKey[i] = XORascii(xored, storedKey);
			}
		}
		return ExpandedKey;
	}

	public int[][] setKey(String key) { // builds key into a hex array by switching to a char[] then looping through
										// array and changing to ascii then hex
		char[] keyAsChars = key.toCharArray();
		for (int i = 0; i < keyAsChars.length; i++) {
			seedKey[i % 4][(int) Math.floor(i / 4)] = (int) keyAsChars[i];
		}
		return seedKey;
	}

	// start to private functions
	// private
	public int[] rotateKey(int[] key) { // one-byte left shift on key
		int firstByte = key[0];
		for (int i = 0; i < key.length - 1; i++) {
			key[i] = key[i + 1];
		}
		key[key.length - 1] = firstByte;
		return key;
	}

	// private
	public int subKey(int key) { // performs substitution on each byte
		String answer = "nothing returned";
		try (PythonInterpreter pyInterp = new PythonInterpreter()) {
			StringWriter output = new StringWriter();
			pyInterp.setOut(output);

			pyInterp.exec("from com.cryptography.project import main");// anything in this will run like its python
			pyInterp.exec("echo(key)");
			answer = output.toString();
		}
		return 1;
	}

	// input 8 bit binary String and RoundKey
	// private
	public int[] XORbinary(int[] key, int[] roundKey) {
		int[] resultingKey = new int[key.length];
		for (int i = 0; i < key.length; i++) {
			resultingKey[i] = (key[i] ^ roundKey[i]);
		}
		return resultingKey;
	}

	// takes arrays of hex strings, XORs them and returns an array of hex strings
	// equaling the XOR result
	public int[] XORascii(int[] value1, int[] value2) {
		int[] answer = new int[value1.length];
		for (int i = 0; i < value1.length; i++) {
			int[] binary1 = toBinaryArray(value1[i]);
			int[] binary2 = toBinaryArray(value2[i]);
			answer[i] = toAscii(XORbinary(binary1, binary2));
		}
		return answer;
	}

	// private
	public int[] roundCon(int roundNumber) {
		String firstByte = Integer.toString(roundNumber) + "b";
		int asciByte = Integer.parseInt(firstByte, 16);
		int asciZero = Integer.parseInt("0", 16);
		int[] rKey = { asciByte, asciZero, asciZero, asciZero };
		return rKey;
	}

	// private -- Checked (without input protection)
	public int[] toBinaryArray(int ascii) {// input of any number of bytes
		// TODO rase error if this happens
		int[] result = new int[8];
		int val = ascii;
		for (int i = 0; i < 8; i++) {
			result[i] = ((val & 128) == 0 ? 0 : 1); // 128 = 1000 0000
			val <<= 1; // val = val << 1

		}

		return result;
	}

	// This function changes 8 bits to ascii
	// private --checked (without input protection)
	public int toAscii(int[] input) { // input 1 8 bite string
		StringBuilder output = new StringBuilder();

		if (input.length != 8) {
			System.out.print("Input not valad, must have length of 8: KeyManager/toHexString");
			// TODO needs to error out saying that input is invalad. Tests needs to be done
			// to
			// check validity
		} else {
			for (int i = 0; i < input.length; i++)
				output.append(String.valueOf(input[i]));
		}
		return Integer.valueOf(output.toString(), 2); //

	}
}
