package data;

public class Customer {
	private final String id;;
	private final String firstName;
	private final String lastName;
	private final String streetName;
	private final String city;
	private final String postalCode;
	private final String phone;
	private final String emailAddress;
	private final String joinDate;

	public static class Builder {
		// required parameters
		private final String id;
		private final String phone;

		// optional
		private String firstName;
		private String lastName;
		private String streetName;
		private String city;
		private String postalCode;
		private String emailAddress;
		private String joinDate;

		public Builder(String id, String phone) {
			this.id = id;
			this.phone = phone;
		}

		public Builder firstName(String val) {
			firstName = val;
			return this;
		}

		public Builder lastName(String val) {
			lastName = val;
			return this;
		}

		public Builder streetName(String val) {
			streetName = val;
			return this;
		}

		public Builder city(String val) {
			city = val;
			return this;
		}

		public Builder postalCode(String val) {
			postalCode = val;
			return this;
		}

		public Builder emailAddress(String val) {
			emailAddress = val;
			return this;
		}

		public Builder joinDate(String val) {
			joinDate = val;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}

	private Customer(Builder builder) {
		id = builder.id;
		firstName = builder.firstName;
		lastName = builder.lastName;
		streetName = builder.streetName;
		city = builder.city;
		postalCode = builder.postalCode;
		phone = builder.phone;
		emailAddress = builder.emailAddress;
		joinDate = builder.joinDate;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getCity() {
		return city;
	}

	public String getPhone() {
		return phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getJoinDate() {
		return joinDate;
	}

}
