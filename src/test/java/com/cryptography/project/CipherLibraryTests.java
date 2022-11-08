package com.cryptography.project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CipherLibraryTests {
	
	public void rotateKeyLeftTest() {
		int[] input = {60, 61, 62, 63};
		int[] answer = {61, 62, 63, 60};
		int[] result = CipherLibrary.rotateKeyLeft(input);
		assertArrayEquals(answer, result);
	}
	
	public void rotateKeyRightTest() {
		int[] input = {60, 61, 62, 63};
		int[] answer = {63, 60, 61, 62};
		int[] result = CipherLibrary.rotateKeyRight(input);
		assertArrayEquals(answer, result);
	}

	public void subBytesTest() {
		int input = 40;
		int answer = 28;
		int result = CipherLibrary.subBytes(input);
		assertEquals(answer, result); 
	}

	public void subBytesInverseTest() {//TODO expected: <40> but was: <24>
		int input = 28;
		int answer = 40;
		int result = CipherLibrary.subBytesInverse(input);
		assertEquals(answer, result); 
	}

	public void shiftRowsTest() {//TODO array contents differ at index [3][1], expected: <108> but was: <121>
		int[][] input = {
				{72,111,108,78},
				{101,87,100,97},
				{108,111,77,109},
				{108,114,121,101}};
		int[][] answer = {
				{72,111,108,78},
				{87,100,97,101},
				{77,109,108,111},
				{101,108,114,121}};
		int[][] result = CipherLibrary.shiftRows(input);
		assertArrayEquals(answer, result);
	}
	
	public void shiftRowsInverseTest() {//TODO array contents differ at index [1][0], expected: <101> but was: <100>
		int[][] input = {
				{72,111,108,78},
				{87,100,97,101},
				{77,109,108,111},
				{101,108,114,121}};
		int[][] answer = {
				{72,111,108,78},
				{101,87,100,97},
				{108,111,77,109},
				{108,114,121,101}};
		int[][] result = CipherLibrary.shiftRowsInverse(input);
		assertArrayEquals(answer, result);
	}
	
	public void mixColumnsTest() {
		
	}
	
	public void mixColumnsInverseTest() {
		
	}
	
	public void XORTest() {
		int[] inputKey = {0, 1, 0, 0, 0, 0, 0, 1};
		int[] inputRKey = {0, 0, 1, 0, 1, 0, 1, 0};
		int[] answer = {0, 1, 1, 0, 1, 0, 1, 1};
		int[] result = CipherLibrary.XORbinary(inputKey, inputRKey);
		assertArrayEquals(answer, result);
	}
	
	public void toBinaryArrayTest() {
		int input = 161;
		int[] answer = {1, 0, 1, 0, 0, 0, 0, 1};
		int[] result = CipherLibrary.toBinaryArray(input);
		assertArrayEquals(answer, result);
	}
	
	public void toAsciiTest() {
		int[] bits = {0, 1, 0, 0, 0, 0, 0, 1};
		int answer = 65;
		int result = CipherLibrary.toAscii(bits);
		assertEquals(answer, result); 
	}
}
