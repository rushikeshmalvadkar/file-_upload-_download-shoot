package com.filetask.builder.example;

public class Application {
	
	public static void main(String[] args) {
		Customer customer = Customer.builder()
				.id(1)
				.name("abhi")
				.build();
		
		System.out.println(customer);
	}

}
