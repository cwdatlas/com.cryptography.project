package com.cryptography.project;

public interface CipherTextI {

	//subBytes takes an matrix and substitutes values with other values
	public int[][] subBytes();
	
	//subBytes takes an matrix and substitutes values with other values resulting in the original values
	public int[][] subBytesInverse();

	//shiftRows moves rows in given matrix
	public int[][] shiftRows();
	
	//shiftRows moves rows in given matrix to the original values
	public int[][] shiftRowsInverse();
	
	//mixColumns moves columns in given matrix
	public int[][] mixColumns();
	
	//mixColumns moves columns in given matrix to the original values
	public int[][] mixColumnsInverse();
	
	//addRoundKey XORs (encrypting) the cipherText using the roundKey 
	//other name used is addRoundKey
	public int[][] encryptKey(int[][] roundKey);
	
	//addRoundKey XORs (encrypting) the cipherText using the roundKey
	//other name used is addRoundKeyInverse
	public int[][] dectrypt(int[][] roundKey);
	
	//turns matrix to double and returns it
	public String getCipherText();
}
