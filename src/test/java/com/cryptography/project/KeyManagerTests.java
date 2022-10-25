package com.cryptography.project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cryptography.project.KeyManager;

public class KeyManagerTests {


	public void getRoundKeyTest() {
		
	}
	

	public void expandKeyTest() {
		
	}
	
	@Test
	public void setKeyTest() {
		String key = "HelloWorldMyName";
		String[][] answer = {
			{"48","6f","6c","4e"},
			{"65","57","64","61"},
			{"6c","6f","4d","6d"},
			{"6c","72","79","65"}};
		KeyManager manager = new KeyManager();
		String[][] result = manager.setKey(key);
		assertArrayEquals(answer, result); 
	}
	
	@Test
	public void rotateKeyTest() {
		String[] input = {"0A", "5F", "6A", "FF"};
		String[] answer = {"5F", "6A", "FF", "0A"};
		KeyManager manager = new KeyManager();
		String[] result = manager.rotateKey(input);
		assertArrayEquals(answer, result);
	}
	
	public void subKeyTest() {
	}
	
	@Test //Kinda works. the data is there, but there is another issue
	public void XORTest() {
		byte[] inputKey = {0, 1, 0, 0, 0, 0, 0, 1};
		byte[] inputRKey = {0, 0, 1, 0, 1, 0, 1, 0};
		byte[] answer = {0, 1, 1, 0, 1, 0, 1, 1};
		KeyManager manager = new KeyManager();
		byte[] result = manager.XOR(inputKey, inputRKey);
		assertArrayEquals(answer, result);
	}

	public void roundConTest() {
		String bytes = "01000001";
		String answer = "A";
		KeyManager manager = new KeyManager();
		String result = manager.toHexString(bytes);
		assertEquals(answer, result); 
	}
	
	@Test
	public void toBinaryStringTest() {
		String input = "A";
		String answer = "01000001";
		KeyManager manager = new KeyManager();
		String result = manager.toBinaryString(input);
		assertEquals(answer, result);
	}
	
	@Test
	public void toHexString() {
		String bytes = "01000001";
		String answer = "A";
		KeyManager manager = new KeyManager();
		String result = manager.toHexString(bytes);
		assertEquals(answer, result); 
	}
	
	
}