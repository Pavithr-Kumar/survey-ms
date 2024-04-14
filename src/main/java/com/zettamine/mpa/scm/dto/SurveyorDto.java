package com.zettamine.mpa.scm.dto;

import com.zettamine.mpa.scm.constants.SurveyConstants;
import com.zettamine.mpa.scm.entity.SurveyCompany;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SurveyorDto {
	private Integer surveyorId;
	
	@NotNull(message = SurveyConstants.NOT_NULL)
	@NotBlank(message = SurveyConstants.NOT_BLANK)
	private String surveyorLicenceId;
	
	@NotNull
	@Pattern(regexp = SurveyConstants.REGEX_TEXT, message = SurveyConstants.VALID_NAME)
	private String firstName;
	
	@NotNull
	@Pattern(regexp = SurveyConstants.REGEX_TEXT, message = SurveyConstants.VALID_NAME)
	private String lastName;
	
	@Email(message = SurveyConstants.VALID_EMAIL)
	private String email;
	
	@Pattern(regexp = SurveyConstants.REGEX_PHONE, message = SurveyConstants.VALID_PHONE)
	private String phone;
	private Boolean geodeticControl;
	private Boolean historicPreservation;
	
	@NotNull(message = SurveyConstants.NOT_NULL)
	private Integer surveyCompanyId;
	private String surveyCompanyName;

}