package io;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import data.Customer;
import data.util.Validator;

enum ColumnDivider {
	Pipe('|'), Colon(':');

	private final char character;

	ColumnDivider(char character) {
		this.character = character;
	}

	public char getCharacter() {
		return character;
	}
}

public class CustomerReader {
	// constraint input data must have 9 columns per entry
	public static LinkedHashMap<String, ArrayList<String>> parseTextToSortedArray(String text) {
		LinkedHashMap<String, ArrayList<String>> customerList = new LinkedHashMap<>();
		ArrayList<String> parsedList = new ArrayList<String>();
		ArrayList<String> customerValue = new ArrayList<String>();
		char[] textChars = text.replace("\"", "").toCharArray();
		// parsing because split not working
		StringBuilder parsedValue = new StringBuilder();
		for (int i = 0; i < textChars.length; i++) {
			if (textChars[i] == ColumnDivider.Pipe.getCharacter()
					|| textChars[i] == ColumnDivider.Colon.getCharacter()) {
				parsedList.add(parsedValue.toString());
				parsedValue.setLength(0);

			} else {
				parsedValue.append(textChars[i]);
				if (i == textChars.length - 1) {
					parsedList.add(parsedValue.toString());
				}
			}
		}
		for (int i = 0; i < parsedList.size(); i++) {
			if (customerValue.size() == 9) {
				customerList.put(customerValue.get(0), new ArrayList<>(customerValue.subList(1, 9)));
				customerValue.clear();
			}
			customerValue.add(parsedList.get(i));
		}
		if (!customerValue.isEmpty()) {
			customerList.put(customerValue.get(0),
					new ArrayList<>(customerValue.subList(1, Math.min(9, customerValue.size()))));
		}

		return customerList;
	}

	public static ArrayList<Customer> mapToCustomerDataTransferObject(
			LinkedHashMap<String, ArrayList<String>> sortedCustomerList) {
		ArrayList<Customer> customerTransferObjectList = new ArrayList<Customer>();
		for (Map.Entry<String, ArrayList<String>> entry : sortedCustomerList.entrySet()) {
			String id = Validator.validateID(entry.getKey());
			String firstName = entry.getValue().get(0);
			String lastName = entry.getValue().get(1);
			String streetName = entry.getValue().get(2);
			String city = entry.getValue().get(3);
			String postalCode = entry.getValue().get(4);
			String phone = entry.getValue().get(5);
			String emailAddress = Validator.validateEmail(entry.getValue().get(6));
			String joinDate = entry.getValue().get(7);
			Customer customer = new Customer.Builder(id, phone).firstName(firstName).lastName(lastName)
					.streetName(streetName).city(city).postalCode(postalCode).emailAddress(emailAddress)
					.joinDate(joinDate).build();
			customerTransferObjectList.add(customer);
		}
		return customerTransferObjectList;

	}

	public static ArrayList<Customer> processInputText(String text) {
		return mapToCustomerDataTransferObject(parseTextToSortedArray(text));
	}

}
