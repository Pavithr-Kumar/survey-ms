package com.zettamine.mpa.scm.dto;

import com.zettamine.mpa.scm.constants.SurveyConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SurveyServiceAreaDto {
	private Integer serviceAreaId;
	
	@Pattern(regexp = SurveyConstants.REGEX_ADDRESS, message = SurveyConstants.VALID_NAME)
    private String county;
	
	@Pattern(regexp = SurveyConstants.REGEX_ADDRESS, message = SurveyConstants.VALID_NAME)
    private String city;
	
	@Pattern(regexp = SurveyConstants.REGEX_ADDRESS, message = SurveyConstants.VALID_NAME)
    private String state;
    
	@Pattern(regexp = SurveyConstants.REGEX_ZIPCODE, message = SurveyConstants.VALID_ZIPCODE)
    private String zipcode;
    
    @NotNull(message = SurveyConstants.NOT_NULL)
    private Integer surveyCompanyId;
    private String surveyCompanyName;
}