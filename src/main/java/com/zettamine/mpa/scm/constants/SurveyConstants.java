package com.zettamine.mpa.scm.constants;

public class SurveyConstants {

	private SurveyConstants() {
		//restrict instantiation
	}
	
	public static final String STATUS_201 = "201";
	public static final String MESSAGE_201 = "Resource created successfully";
	
	public static final String STATUS_200 = "200";
	//public static final String MESSAGE_200 = "Request processed successfully";
	public static final String MESSAGE_200 = "Survey Company updated successfully";
	
	public static final String STATUS_409 = "409";
	public static final String MESSAGE_409 = "Resource Already exists";
	
	public static final String STATUS_417 = "417";
	public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact dev team";
	public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact dev team";
	
	public static final String STATUS_500 = "500";
	public static final String MESSAGE_500 = "Please try again";
 
}
