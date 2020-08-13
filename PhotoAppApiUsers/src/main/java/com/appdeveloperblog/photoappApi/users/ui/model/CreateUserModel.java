package com.appdeveloperblog.photoappApi.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserModel {

	@NotNull(message = "First name cannot be null")
	@Size(min = 2, message = "first name must not be less than 2 char's")
	private String firstName;
	@NotNull(message = "Last name cannot be null")
	@Size(min = 2, message = "Last name must not be less than 2 char's")
	private String lastName;
	@Email
	private String email;
	@Size(min = 8, max = 16, message = "must be between 2-8 char's")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
