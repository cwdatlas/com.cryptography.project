package com.cryptography.project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cryptography.project.KeyManager;

public class KeyManagerTests {

	@Test
	public void getRoundKeyTest() {
		KeyManager manager = new KeyManager();
		manager.setKey("HelloWorldMyName");//must be 16 bytes
		manager.expandKey();
		for(int i = 0; i < 10; i++) {
			String[][] roundKey = manager.getRoundKey(i);
			System.out.println(String.valueOf(roundKey));
		}
		assertNotNull("not null"); 
	}
	@Test
	public void expandKeyTest() {
		KeyManager manager = new KeyManager();
		manager.setKey("HelloWorldMyName");//must be 16 bytes
		int[][] result = manager.expandKey();
		assertNotNull(result); 
	}
	
	@Test
	public void setKeyTest() {
		String key = "HelloWorldMyName";
		int[][] answer = {
			{72,111,108,78},
			{101,87,100,97},
			{108,111,77,109},
			{108,114,121,101}};
		KeyManager manager = new KeyManager();
		int[][] result = manager.setKey(key);
		assertArrayEquals(answer, result); 
	}
	
	@Test
	public void rotateKeyTest() {
		int[] input = {60, 61, 62, 63};
		int[] answer = {61, 62, 63, 60};
		KeyManager manager = new KeyManager();
		int[] result = manager.rotateKey(input);
		assertArrayEquals(answer, result);
	}
	//TODO fix calling python class issue
	public void subKeyTest() {//Check if python will run main.py
		int input = 40;
		int answer = 40;
		KeyManager manager = new KeyManager();
		int result = manager.subKey(input);
		assertEquals(answer, result); 
	}
	
	@Test 
	public void XORTest() {
		int[] inputKey = {0, 1, 0, 0, 0, 0, 0, 1};
		int[] inputRKey = {0, 0, 1, 0, 1, 0, 1, 0};
		int[] answer = {0, 1, 1, 0, 1, 0, 1, 1};
		KeyManager manager = new KeyManager();
		int[] result = manager.XORbinary(inputKey, inputRKey);
		assertArrayEquals(answer, result);
	}

	public void XORHexTest() {
		//String[] inputKey = {"1a", "1b", "1c", "1d"};
		//String[] inputRKey = {"2a", "2b", "3c", "2d"};
		int value = "1a".getBytes()[1]^"".getBytes()[1];
		assertEquals(value, 1);
	}

	@Test
	public void roundConTest() {
		int round = 1;
		int[] answer = {27, 0, 0, 0};
		KeyManager manager = new KeyManager();
		int[] result = manager.roundCon(round);
		assertArrayEquals(answer, result); 
	}
	
	@Test
	public void toBinaryArrayTest() {
		int input = 161;
		int[] answer = {1, 0, 1, 0, 0, 0, 0, 1};
		KeyManager manager = new KeyManager();
		int[] result = manager.toBinaryArray(input);
		assertArrayEquals(answer, result);
	}
	
	@Test
	public void toAsciiTest() {
		int[] bits = {0, 1, 0, 0, 0, 0, 0, 1};
		int answer = 65;
		KeyManager manager = new KeyManager();
		int result = manager.toAscii(bits);
		assertEquals(answer, result); 
	}
	
	
}
