package io;

import java.util.ArrayList;
import java.util.Scanner;

import data.Customer;

enum Headers {
	Index("#.", 2), Id("ID", 10), FirstName("First name", 14), LastName("Last name", 14), Street("Street", 25),
	City("City", 15), PostalCode("Postal Code", 14), Phone("Phone", 20), Email("Email", 30), JoinDate("Join Date", 10);

	private final String name;
	private final int length;

	Headers(String name, int length) {
		this.name = name;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}
}

public class CustomerReport {

	public static String generateHeaders() {
		StringBuilder headerString = new StringBuilder("Customer Report\n");
		headerString.append("-".repeat(160)).append("\n");
		for (Headers header : Headers.values()) {
			headerString.append(String.format("%-" + header.getLength() + "s", header.getName()));
		}
		headerString.append("\n");
		headerString.append("-".repeat(160)).append("\n");
		return headerString.toString();
	}

	public static String formatText(ArrayList<Customer> dataObjectList) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < dataObjectList.size(); i++) {
			Customer customer = dataObjectList.get(i);
			stringBuilder.append(formatCustomer(i + 1, customer)).append("\n");
		}
		return stringBuilder.toString();
	}

	private static String formatCustomer(Number index, Customer customer) {
		StringBuilder formattedCustomer = new StringBuilder();
		for (Headers header : Headers.values()) {
			String value;
			switch (header) {
			case Index:
				value = index.toString() + ".";
				break;
			case Id:
				value = customer.getId();
				break;
			case FirstName:
				value = customer.getFirstName();
				break;
			case LastName:
				value = customer.getLastName();
				break;
			case Street:
				value = customer.getStreetName();
				break;
			case City:
				value = customer.getCity();
				break;
			case PostalCode:
				value = customer.getPostalCode();
				break;
			case Phone:
				value = customer.getPhone();
				break;
			case Email:
				value = customer.getEmailAddress();
				break;
			case JoinDate:
				value = customer.getJoinDate();
				break;
			default:
				value = "";
				break;
			}
			formattedCustomer.append(String.format("%-" + header.getLength() + "s", value));
		}
		return formattedCustomer.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.print("Enter some text (or type 'exit' to quit): ");

			String inputText = scanner.nextLine();

			if ("exit".equalsIgnoreCase(inputText.trim())) {
				exit = true;
			} else if (inputText.length() == 0) {
				System.out.println("Usage: must provide customer info as command argument.");
			} else {
				ArrayList<Customer> dataObjectText = CustomerReader.processInputText(inputText);
				System.out.print(generateHeaders());
				System.out.print(formatText(dataObjectText));
				System.exit(0);
			}
		}
		scanner.close();
		System.exit(0);
	}

}
