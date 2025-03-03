package com.stockpin.project.util;

public class Fomatter {

	public static long parseLong(String num) {
		String numStr = num.split("\\.")[0].trim();
		long result = Long.parseLong(numStr);
		return result;
	}
	
	public static double parseDouble(String num) {
		double result = Double.parseDouble(num);
		return result;
	}
	
}
