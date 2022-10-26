package com.cryptography.project;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.python.util.PythonInterpreter;

public class KeyManager implements KeyManagerI {
	private String[][] byteKey = new String[4][4];
	private String[][] ExpandedKey = new String[44][4]; // TODO set to actual size

	// Constructor, so if you need to initialize any variables do it here
	// Also, we are wanting to have our key and key variables to be in hex, so you
	// will have
	// to change the variable declarations to the hexNumber variable or whatever it
	// takes
	// to have a hex variable (add the library to dependencies in Pom file if you
	// need to
	public KeyManager() {

	}
	//TODO update so roundNumber is hashed and then turned to a hex and placed in the array
	public String[] getRoundKey(int roundNumber) {// TODO create section of key creation based on
		return null;
	}

	public String[][] expandKey() {
		// first step is to loop through the unfilled expandedKey to fill it
		String[] storedKey;
		String[] xored = null;
		for (int i = 4; i < ExpandedKey.length; i++) {
			storedKey = ExpandedKey[i - 1];
			if (i % 4 == 0) {
				storedKey = rotateKey(storedKey);
				xored = roundCon(i/4);
			}else {
				xored = ExpandedKey[i-4];
			}
				
			for(int l = 0; l < storedKey.length; l++) {
				byte[] key = toBinaryString(storedKey[l]).getBytes();
				byte[] rc = toBinaryString(xored[l]).getBytes();
				storedKey[l] = XOR(key, rc).toString();
			}
			ExpandedKey[i] = storedKey;
		}
		return ExpandedKey;
	}

	public String[][] setKey(String key) { // builds key into a hex array by switching to a char[] then looping through
										// array and changing to ascii then hex
		char[] keyAsChars = key.toCharArray();
		for (int i = 0; i < keyAsChars.length; i++) {
			byteKey[i % 4][(int) Math.floor(i / 4)] = Integer.toHexString((int) keyAsChars[i]);
		}
		return byteKey;
	}

	// start to private functions
	//private
	public String[] rotateKey(String[] key) { // one-byte left shift on key
		String firstByte = key[0];
		for (int i = 0; i < key.length - 1; i++) {
			key[i] = key[i + 1];
		}
		key[key.length - 1] = firstByte;
		return key;
	}
	//private
	public String subKey(String key) { // performs substitution on each byte
	    String answer = "nothing returned";
		try (PythonInterpreter pyInterp = new PythonInterpreter()) {
	        StringWriter output = new StringWriter();
	        pyInterp.setOut(output);

	        pyInterp.exec("from com.cryptography.project import main");//anything in this will run like its python
	        pyInterp.exec("echo(key)");
	        answer = output.toString();
		}
		
		return answer;
	}
	//input 8 bit binary String and RoundKey
	//private
	public byte[] XOR(byte[] key, byte[] roundKey) {
		byte[] resultingKey = new byte[key.length]; 
		for(int i=0; i<key.length; i++) {
			resultingKey[i] = (byte) (key[i]^roundKey[i]);
		}
		return resultingKey;
	}
	//private
	public String[] roundCon(int roundNumber) {
		String firstByte = Integer.toString(roundNumber)+"b";
		String[] rKey = {firstByte, "0", "0", "0"};
		return rKey;
	}
	//private -- Checked (without input protection)
	public String toBinaryString(String s) {//input of any number of bytes
		byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
		    int val = b;                                      // byte -> int
		    for (int i = 0; i < 8; i++) {
		        result.append((val & 128) == 0 ? 0 : 1);      // 128 = 1000 0000
		        val <<= 1;                                    // val = val << 1
		    }
		}

		return result.toString();
	}
	//This class changes 8 bytes to its plain text equivalent
	//private --checked (without input protection)
	public String toHexString(String input) { //input 1 8 bite string
        //String input = "01001000"; = example
		String raw = "hello";
		if(input.length() != 8) {
			System.out.print("Input not valad, must have length of 8: KeyManager/toHexString");
			//needs to error out saying that input is invalad. Tests needs to be done to check validity
		}else {
        // Java 8 makes life easier
			raw = Arrays.stream(input.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining()); // cut the space
		}
        return raw; //

    }
}
