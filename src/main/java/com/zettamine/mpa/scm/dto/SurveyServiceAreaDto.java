package com.zettamine.mpa.scm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SurveyServiceAreaDto {
	private Integer serviceAreaId;
	
	@Pattern(regexp = "^[A-Za-z\s]+$")
    private String county;
	
	@Pattern(regexp = "^[A-Za-z\s]+$")
    private String city;
	
	@Pattern(regexp = "^[A-Za-z\s]+$")
    private String state;
    
    @Pattern(regexp = "^([0-9]{5})?$")
    private String zipcode;
    
    @NotNull
    private Integer surveyCompanyId;
    private String surveyCompanyName;
}
