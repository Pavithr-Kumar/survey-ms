package com.zettamine.mpa.scm.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zettamine.mpa.scm.constants.SurveyConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data

public class SurveyCompanyDto {
	private Integer surveyCompanyId;
	
	@NotNull(message = SurveyConstants.PROVIDE_VALUE)
	private String companyName;
	
	private String address;
	
	@Pattern(regexp = "^[A-Za-z\s]+$")
	private String city;
	
	@Pattern(regexp = "^[A-Za-z\s]+$")
	private String state;
	
	@Pattern(regexp = "^([0-9]{5})?$")
	private String zipcode;
	

	@Pattern(regexp = "^[A-Za-z\s]+$")

	private String country;
	
	@NotNull
	@Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$")
	private String phone;
	
	@NotNull
	@Email
	private String email;
	
	private String website;
	private String notes;
	
	private Boolean providesTriangulationService;
	
	private List<String> surveyTypeNamesList = new ArrayList<>();
	

}
