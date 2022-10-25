package com.cryptography.project;

//All input and output variables are subject to change
public interface KeyManagerI {

	//getRoundKey calculates the round key from the key and the roundNumber
	public String[][] getRoundKey(int roundNumber);
	
	//expands the key from the original key
	public boolean expandKey();
	
	//sets key
	public String[][] setKey(String key);
	
}
