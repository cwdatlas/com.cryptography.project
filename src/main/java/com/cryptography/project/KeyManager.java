package com.cryptography.project;

import java.io.StringWriter;
import org.python.util.PythonInterpreter;

public class KeyManager implements KeyManagerI {
	private int[][] seedKey = new int[4][4];
	private int[][] ExpandedKey = new int[44][4];

	public KeyManager() {

	}

	public int[][] getRoundKey(int roundNumber) {
		int[][] roundKey = new int[4][4];
		int position = roundNumber * 4;
		for (int i = 0; i < 4; i++) {
			for (int l = 0; l < 4; l++)
				roundKey[i][l] = ExpandedKey[(i) + position][(l)];
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
					storedKey = CipherLibrary.rotateKeyLeft(storedKey);
					for(int l = 0; l < 4; l++)
						storedKey[l] = CipherLibrary.subBytes(storedKey[l]);
					xored = roundCon(i / 4);
				} else {
					xored = ExpandedKey[i - 4];
				}
				ExpandedKey[i] = CipherLibrary.XORascii(xored, storedKey);
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


	// private
	public int[] roundCon(int roundNumber) {
		String firstByte = Integer.toString(roundNumber) + "b";
		int asciByte = Integer.parseInt(firstByte, 16);
		int asciZero = Integer.parseInt("0", 16);
		int[] rKey = { asciByte, asciZero, asciZero, asciZero };
		return rKey;
	}
}
