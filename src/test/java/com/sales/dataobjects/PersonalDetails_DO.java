package com.sales.dataobjects;

public class PersonalDetails_DO {
	public String salutation;
	public String title;
	public String firstName;
	public String lastName;
	public String email;
	public String phone;
	public String dateOfBirth;
	public String companyName;
	public String roleInCompany;

	public PersonalDetails_DO() {
		salutation = "Herr";
		title = "Dr.";
		firstName = "TEST_USER";
		lastName = "TEST_USER";
		email = "test_user@gmail.com";
		phone = "+4915163612330";
		dateOfBirth = "10.10.1988";
		companyName	= "ABC Consultant";
		roleInCompany = "Vorstand";
	}
}