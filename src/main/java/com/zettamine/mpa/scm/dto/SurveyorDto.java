package com.zettamine.mpa.scm.dto;

import com.zettamine.mpa.scm.entity.SurveyCompany;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SurveyorDto {
	private Integer surveyorId;
	
	@NotNull
	private String surveyorLicenceId;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")
	private String firstName;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String phone;
	private Boolean geodeticControl;
	private Boolean historicPreservation;
	
	//@NotNull
	private Integer surveyCompanyId;
	private String surveyCompanyName;

}
