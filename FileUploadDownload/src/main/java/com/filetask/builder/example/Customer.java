package com.filetask.builder.example;

public class Customer {

	private Integer id;
	private String name;
	private Integer age;

	public Customer(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public static CustomerBuilder builder() {
		return new CustomerBuilder();
	}

	static class CustomerBuilder {
		private Integer id;
		private String name;
		private Integer age;

		public CustomerBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public CustomerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CustomerBuilder age(Integer age) {
			this.age = age;
			return this;
		}

		public Customer build() {
			return new Customer(id, name, age);
		}

	}
}
