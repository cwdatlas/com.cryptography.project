package com.cryptography.project;

public class CipherLibrary {
	private static final String[][] hexTable = {
			{ "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76" },
			{ "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "CD" },
			{ "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15" },
			{ "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75" },
			{ "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84" },
			{ "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF" },
			{ "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8" },
			{ "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2" },
			{ "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73" },
			{ "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB" },
			{ "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79" },
			{ "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08" },
			{ "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A" },
			{ "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E" },
			{ "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF" },
			{ "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" } };

	private static final String[][] hexTableInverse = {
			{ "52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb" },
			{ "7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb" },
			{ "54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e" },
			{ "08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2",	"49", "6d", "8b", "d1", "25" },
			{ "72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92" },
			{ "6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84" },
			{ "90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06" },
			{ "d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b" },
			{ "3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73" },
			{ "96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e" },
			{ "47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b" },
			{ "fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4" },
			{ "1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f" },
			{ "60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef" },
			{ "a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61" },
			{ "17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d" }};

	public static int subBytes(int ascii) {
		char[] hexs = Integer.toHexString(ascii).toCharArray();
		int y = Integer.parseInt(String.valueOf(hexs[0]), 16);
		int x = Integer.parseInt(String.valueOf(hexs[1]), 16);
		String substitution = hexTable[y][x]; // This might need to be switched
		return Integer.parseInt(substitution, 16);
	}

	public static int subBytesInverse(int ascii) {
		char[] hexs = Integer.toHexString(ascii).toCharArray();
		int y = Integer.parseInt(String.valueOf(hexs[0]), 16);
		int x = Integer.parseInt(String.valueOf(hexs[1]), 16);
		String substitution = hexTableInverse[y][x]; // This might need to be switched
		return Integer.parseInt(substitution, 16);
	}

	public static int[][] shiftRows(int[][] matrix) {
		for(int i = 0; i<2; i++) {
			//rotate second row
			int a = matrix[2][i];
			matrix[2][i] = matrix[2][i+2];
			matrix[2][i+2] = a;
		}
		matrix[1] = rotateKeyLeft(matrix[1]); //rotate left
		matrix[3] = rotateKeyRight(matrix[3]); //rotate right
		return matrix;
	}
	
	public static int[][] shiftRowsInverse(int[][] matrix) {
		for(int i = 0; i<2; i++) {
			//rotate second row
			int a = matrix[2][i];
			matrix[2][i] = matrix[2][i+2];
			matrix[2][i+2] = a;
		}
		matrix[1] = rotateKeyRight(matrix[1]); //rotate left
		matrix[3] = rotateKeyLeft(matrix[3]); //rotate right
		return matrix;
	}
	//TODO
	public static int[][] mixColumns(int[][] matrix){
		
		return matrix;
	}
	//TODO
	public static int[][] mixColumnsInverse(int[][] matrix){
		return matrix;
	}
	
	//After this there are helper functions
	// private -- Checked (without input protection)
	public static int[] toBinaryArray(int ascii) {// input of any number of bytes
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
	public static int toAscii(int[] input) { // input 1 8 bite string
		StringBuilder output = new StringBuilder();

		if (input.length != 8) {
			System.out.print("Input not valad, must have length of 8: KeyManager/toHexString");
			// TODO needs to error out saying that input is invalad. Tests needs to be done
		} else {
			for (int i = 0; i < input.length; i++)
				output.append(String.valueOf(input[i]));
		}
		return Integer.valueOf(output.toString(), 2); //
	}
	
	// input 8 bit binary String and RoundKey
	// protected
	public static int[] XORbinary(int[] key, int[] roundKey) {
		int[] resultingKey = new int[key.length];
		for (int i = 0; i < key.length; i++) {
			resultingKey[i] = (key[i] ^ roundKey[i]);
		}
		return resultingKey;
	}

	// takes arrays of hex strings, XORs them and returns an array of hex strings
	// equaling the XOR result
	public static int[] XORascii(int[] value1, int[] value2) {
		int[] answer = new int[value1.length];
		for (int i = 0; i < value1.length; i++) {
			int[] binary1 = toBinaryArray(value1[i]);
			int[] binary2 = toBinaryArray(value2[i]);
			answer[i] = toAscii(XORbinary(binary1, binary2));
		}
		return answer;
	}
	
	public static int[] rotateKeyLeft(int[] key) { // one-byte left shift on key
		int firstByte = key[0];
		for (int i = 0; i < key.length - 1; i++) {
			key[i] = key[i + 1];
		}
		key[key.length - 1] = firstByte;
		return key;
	}
	
	public static int[] rotateKeyRight(int[] key) { // one-byte left shift on key
		int firstByte = key[0];
		for (int i = 0; i < key.length - 1; i++) {
			key[i] = key[3 - i];
		}
		key[key.length - 1] = firstByte;
		return key;
	}
}