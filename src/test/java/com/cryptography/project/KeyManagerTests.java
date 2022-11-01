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
			int[][] roundKey = manager.getRoundKey(i);
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

	public void XORHexTest() {
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
}
