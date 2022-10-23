package com.cryptography.project;

//All input and output variables are subject to change
public interface KeyManagerI {

	//getRoundKey calculates the round key from the key and the roundNumber
	public int[][] getRoundKey(int roundNumber);
	
	//expands the key from the original key
	public boolean expandKey();
	
	//generates and sets key internally as a private variable 
	public void genorateKey();
	
	//sets key (primarily for decrypting)
	public void setKey(String key);
	
}
