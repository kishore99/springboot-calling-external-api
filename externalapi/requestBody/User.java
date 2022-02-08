package com.springbootapp.externalapi.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty("gender")
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [gender=" + gender + "]";
	}
	
	
}
