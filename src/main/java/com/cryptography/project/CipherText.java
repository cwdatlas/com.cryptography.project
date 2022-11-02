package com.cryptography.project;

public class CipherText implements CipherTextI {
	int[][] workingText = new int[4][4]; 

	// gets ciphertext and turns it into matrix and saves it as a private variable
	public CipherText(String cipherText) {
		int length = cipherText.length();
		if(cipherText.length() != 16) {
			System.out.println("CipherText inisalized with string not of 16 bytes");
		}else {
			char[] keyAsChars = cipherText.toCharArray();
			for (int i = 0; i < keyAsChars.length; i++) {
				workingText[i % 4][(int) Math.floor(i / 4)] = (int) keyAsChars[i];
			}
		}
	}

	//Loops through values and substitutes them 
	public int[][] subBytes() {
		for(int i = 0; i < 4; i++) 
			for(int l = 0; l < 4; l++) 
				workingText[i][l] = CipherLibrary.subBytes(workingText[i][l]);
		return workingText;
	}
	//built
	public int[][] subBytesInverse() {
		for(int i = 0; i < 4; i++) 
			for(int l = 0; l < 4; l++) 
				workingText[i][l] = CipherLibrary.subBytesInverse(workingText[i][l]);
		return workingText;
	}
	//built
	public int[][] shiftRows() {
		return CipherLibrary.shiftRows(workingText);
	}
	//built
	public int[][] shiftRowsInverse() {
		return CipherLibrary.shiftRowsInverse(workingText);
	}

	public int[][] mixColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[][] mixColumnsInverse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int[][] encryptKey(int[][] roundKey) {
		for(int i = 0; i < 4; i++) 
			workingText[i] = CipherLibrary.XORascii(workingText[i], roundKey[i]);
		return workingText;
	}

	public int[][] dectryptKey(int[][] roundKey) {
		for(int i = 0; i < 4; i++) 
			workingText[i] = CipherLibrary.XORascii(workingText[i], roundKey[i]);
		return workingText;
	}

	public String getWorkingText() {
		StringBuffer text = new StringBuffer();
		for(int i = 0; i < 4; i++)
			for(int l = 0; l < 4; l++)
				text.append( (char) workingText[l][i]);
		return text.toString();
	}

}
