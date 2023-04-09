package com.filetask.builder.example;

public class StringBuilderExample {
	
	public static void main(String[] args) {
		/*
		 * Genrate SQL/JPQL query
		 * Generate Html code as string
		 * Generate Xml code as string
		 */
		usingStringForConcat();
		usingStringBuilderForConcat();
		
	}

	private static void usingStringBuilderForConcat() {
		long start = System.currentTimeMillis();
		String name = "abhi";
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 1000000; i++) {
			stringBuilder.append("hello");
			stringBuilder.append(name);
		}
		name = stringBuilder.toString();
		long end = System.currentTimeMillis();
		System.out.println("Time taken using StringBuilder : " + (end - start) + "ms");
	}

	private static void usingStringForConcat() {
		long start = System.currentTimeMillis();
		String name = "abhi";
		for (int i = 0; i < 100000; i++) {
			name = "hello" + name;
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken using String : " + (end - start) + "ms");
	}

}
