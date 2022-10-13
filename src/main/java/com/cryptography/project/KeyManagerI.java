package com.cryptography.project;

//All input and output variables are subject to change
public interface KeyManagerI {

	//getRoundKey calculates the round key from the key and the roundNumber
	public double getRoundKey(int roundNumber);
	
	//expands the key from the original key to a key that round keys can be gathered from
	public boolean expandKey();
	
	//generates and sets key internally as a private variable 
	public double genorateKey();
	
	
}
