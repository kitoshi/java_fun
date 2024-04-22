package data.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Email {
	NotAvailable;

	@Override
	public String toString() {
		return "N/A";
	}
}

public class Validator {
	public static String validateEmail(String email) {
		String regex = ".+@.+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (email == null || email.isEmpty() || !matcher.matches()) {
			return Email.NotAvailable.toString();
		}
		return email;
	}

	public static String validateID(String id) {
		int idLength = id.length();
		StringBuilder formattedID = new StringBuilder();
		for (int i = 0; i < 6 - idLength; i++) {
			formattedID.append('0');
		}
		formattedID.append(id);
		return formattedID.toString();
	}
}
