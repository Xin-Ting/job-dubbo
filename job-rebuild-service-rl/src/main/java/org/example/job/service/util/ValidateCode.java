package org.example.job.service.util;

import java.util.Random;

/**
 * @author 85217
 */
public class ValidateCode {

	public static String generateCode() {
		return generate(6);
	}

	public static String generate(int length) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}